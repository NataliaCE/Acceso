package ejemplo1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
//
// Alberto Carrera Martín - Abril 2020
//

public class AccesoBdatos {
	private EntityManagerFactory emf;
	private EntityManager em; //Contiene los datos de conexión
	
	public void conectar() {
		emf = Persistence.createEntityManagerFactory("db/empleados.odb");
		em = emf.createEntityManager();
	}
	public void desconectar() {
		em.close();
		emf.close();
	}
	public DepartamentoEntity buscarDepartamento(int numDepartamento) {
		return em.find(DepartamentoEntity.class, numDepartamento);
	}// de método buscarDepartamento
	//
	@SuppressWarnings("deprecation")
	public void imprimirDepartamento (int numDepartamento) {
		DepartamentoEntity d = buscarDepartamento(numDepartamento);
		if (d==null)
			System.out.println("No existe el Departamento " + numDepartamento);
		else {
			Set <EmpleadoEntity> empleados =d.getEmpleados();
			String datos="Datos del departamento " + numDepartamento + ": ";
			datos+= "Nombre: " + d.getNombre() + " - Localidad: " + d.getLocalidad()+ "\n";
			if (empleados.isEmpty())
				datos+="No tiene empleados en este momento";
			else {			
				datos+="Lista de empleados"+ "\n";
				datos+="*******************";
			}
			for (EmpleadoEntity empleado :empleados) {
				datos+= "\nNúmero de empleado: " + empleado.getEmpnoId()+ "\n";
				datos+= "Nombre: " + empleado.getNombre()+ "\n";
				datos+= "Oficio: " + empleado.getOficio()+ "\n";
				if (empleado.getDirId()==null)
					datos+= "Jefe: No tiene"+ "\n";
				else
					datos+= "Jefe: "+ empleado.getDirId().getNombre()+ "\n";
				datos+= "Año de alta: " + (empleado.getAlta().getYear()+1900)+ "\n";	
				datos+= "Salario: "+ empleado.getSalario()+ "\n";
				if (empleado.getComision() ==null)
					datos+= "Comisión: No tiene"+ "\n";
				else
					datos+= "Comisión: "+ empleado.getComision()+ "\n";
			}
			
			System.out.println(datos);
		}
	} // de método imprimirDepartamento
	
	public boolean insertarDepartamento (DepartamentoEntity d) {
		if (buscarDepartamento(d.getDptoId())!=null)
			return false;
		em.getTransaction().begin();
		em.persist(d);
		em.getTransaction().commit();
		return true;
	} // de insertarDepartamento
	
	public boolean modificarDepartamento (DepartamentoEntity d) {
		DepartamentoEntity departamentoBuscado=buscarDepartamento(d.getDptoId());
		if (departamentoBuscado==null)
			return false;
		em.getTransaction().begin();
		departamentoBuscado.setNombre(d.getNombre());
		departamentoBuscado.setLocalidad(d.getLocalidad());
		em.persist (departamentoBuscado);
		em.getTransaction().commit();
		return true;
	} // de modificarDepartamento
	
	// Si tiene empleados lo borraría igual, dejando en estos el número de Departamento
	// pero el resto de los atributos del departamento a null. Por eso no dejo borrar
	
	public boolean borrarDepartamento  (int numDepartamento) {
		DepartamentoEntity departamentoBuscado=buscarDepartamento(numDepartamento);
		if (departamentoBuscado==null || !departamentoBuscado.getEmpleados().isEmpty() )
			return false;
		em.getTransaction().begin();
		em.remove(departamentoBuscado);
		em.getTransaction().commit();
		return true;
	} // de modificarDepartamento
	
	public void demoJPQL() {
		
		Query q1 = em.createQuery("SELECT COUNT(d) FROM DepartamentoEntity d");
        System.out.println("Total Departamentos: " + q1.getSingleResult());
        //
        TypedQuery<Long> tq1 = em.createQuery(
        	      "SELECT COUNT(d) FROM DepartamentoEntity d", Long.class);
        System.out.println("Total Departamentos: " + tq1.getSingleResult());
        //
        TypedQuery<DepartamentoEntity>tq2 =
	            em.createQuery("SELECT d FROM DepartamentoEntity d", DepartamentoEntity.class);
	        List<DepartamentoEntity> l2 = tq2.getResultList();
	        for (DepartamentoEntity r2 : l2) {
	            System.out.println("Nombre :  " + r2.getNombre()+ ", Localidad: "+ r2.getLocalidad());
	        }
	    //
        TypedQuery<Object[]>tq3 =
	            em.createQuery("SELECT d.nombre, d.localidad FROM DepartamentoEntity  d", Object[].class);
	        List<Object[]> l3 = tq3.getResultList();
	        for (Object[] r3 : l3) {
	            System.out.println(
	            "Nombre :  " + r3[0] + ", Localidad: " + r3[1]);
	    }    
	    //*/
	      TypedQuery<Object[]>tq4 =
		            em.createQuery("SELECT d.nombre, d.localidad FROM DepartamentoEntity d"
		            		+ " WHERE d.dptoId != :n", Object[].class);
	        		tq4.setParameter("n", 10);
		        List<Object[]> l4 = tq4.getResultList();
		        for (Object[] r4 : l4) {
		            System.out.println(
		            "Nombre :  " + r4[0] + ", Localidad: " + r4[1]);
		    }     
	     
	}// de demoJPQL
//--------------------------------------------------------------------------------------------------------------
	
	public void ejercicio8_1() {
		TypedQuery<EmpleadoEntity> tq1 = em.createQuery("SELECT e FROM EmpleadoEntity e", EmpleadoEntity.class);
		List<EmpleadoEntity> listaEmpleados = tq1.getResultList();
		for(EmpleadoEntity e : listaEmpleados) {
			System.out.println(e.getNombre() + " - " + e.getEmpnoId() + " - " + e.getDirId());
		}
	}
	
	public void ejercicio8_2() {
		TypedQuery<EmpleadoEntity> tq = em.createQuery("SELECT e FROM EmpleadoEntity e "
				+ "WHERE UPPER(e.nombre) LIKE UPPER('%carrera%')", EmpleadoEntity.class);
		List<EmpleadoEntity> listaEmpleados = tq.getResultList();
		for(EmpleadoEntity e : listaEmpleados) {
			System.out.println(e.getNombre() + " - " + e.getAlta());
		}
	}
	
	public void ejercicio8_3() {
		TypedQuery<EmpleadoEntity> tq = em.createQuery("SELECT e FROM EmpleadoEntity e "
				+ "WHERE e.departamento.dptoId = 20 AND e.oficio = 'Empleado", EmpleadoEntity.class);
		List<EmpleadoEntity> listaEmpleados = tq.getResultList();
		for(EmpleadoEntity e : listaEmpleados) {
			System.out.println(e.getNombre() + " - " + e.getOficio() + " - " + e.getDepartamento().getNombre());
		}
	}
	
	public void ejercicio8_4() {
		TypedQuery<EmpleadoEntity> tq = em.createQuery("SELECT e FROM EmpleadoEntity e ", EmpleadoEntity.class);
		List<EmpleadoEntity> listaEmpleados = tq.getResultList();
		Calendar c = Calendar.getInstance();
		for(EmpleadoEntity e : listaEmpleados) {
			c.setTime(e.getAlta());
			if(c.get(Calendar.YEAR) >= 2003) {
				System.out.println(e.getNombre() + " - " + e.getAlta());
			}
		}
	}
	
	public void ejercicio8_5() {
		TypedQuery<EmpleadoEntity> tq = em.createQuery("SELECT e FROM EmpleadoEntity e "
				+ "ORDER BY e.departamento.nombre", EmpleadoEntity.class);
		List<EmpleadoEntity> listaEmpleados = tq.getResultList();
		for(EmpleadoEntity e : listaEmpleados) {
			System.out.println(e.getDepartamento().getNombre() + " - " + e.getNombre());
		}
	}
	
	public void ejercicio8_6() {
		TypedQuery<DepartamentoEntity> tq = em.createQuery("SELECT d FROM DepartamentoEntity d", DepartamentoEntity.class);
		List<DepartamentoEntity> lista = tq.getResultList();
		for(DepartamentoEntity d : lista) {
			if(!d.getEmpleados().isEmpty()) {
				int salarioMax = 0;
				int salarioTotal = 0;
				for(EmpleadoEntity e : d.getEmpleados()) {
					salarioTotal =+ e.getSalario();
					if(salarioMax < e.getSalario()) salarioMax = e.getSalario();
				}
				System.out.println(d.getNombre() + " - " + d.getEmpleados().size() + " - " + salarioTotal +
						" - " + salarioMax);
			}
		}
	}
	
	public void ejercicio8_7() {
		TypedQuery<Object[]> tq = em.createQuery("SELECT e.departamento.nombre, COUNT(e.empnoId), SUM(e.salario), MAX(e.salario)"
				+ " FROM EmpleadoEntity e GROUP BY e.departamento HAVING COUNT(e.empnoId) > 4", Object[].class);
		List<Object[]> lista = tq.getResultList();
		for(Object[] fila : lista) {
			System.out.println(fila[0] + " - " + fila[1] + " - " + fila[2] + " - " + fila[3]);
		}
	}
	
	public void ejercicio8_8() {
		TypedQuery<EmpleadoEntity> tq1 = em.createQuery("SELECT e FROM EmpleadoEntity e", EmpleadoEntity.class);
		List<EmpleadoEntity> listaEmpleados = tq1.getResultList();
		for(EmpleadoEntity e : listaEmpleados) {
			if(e.getDirId() != null) {
				System.out.println(e.getNombre() + " -> Jefe: " + e.getDirId().getNombre() + 
						", Departamento: " + e.getDepartamento().getDptoId());
			}
		}
	}
	
	public void ejercicio8_9() {
		TypedQuery<DepartamentoEntity> tq = em.createQuery("SELECT d FROM DepartamentoEntity d", DepartamentoEntity.class);
		List<DepartamentoEntity> lista = tq.getResultList();
		for(DepartamentoEntity d : lista) {
			if(!d.getEmpleados().isEmpty()) {
				System.out.println(d.getNombre() + " - " + d.getEmpleados().size());
			}
		}
	}
	
	public void ejercicio8_10() {
		TypedQuery<DepartamentoEntity> tq = em.createQuery("SELECT d FROM DepartamentoEntity d", DepartamentoEntity.class);
		List<DepartamentoEntity> lista = tq.getResultList();
		for(DepartamentoEntity d : lista) {
			System.out.println(d.getNombre() + " - " + d.getEmpleados().size());
		}
	}
	
	public void ejercicio8_11() {
		TypedQuery<EmpleadoEntity> tq = em.createQuery("SELECT e FROM EmpleadoEntity e "
				+ "ORDER BY e.departamento.dptoId DESC, e.salario ASC", EmpleadoEntity.class);
		List<EmpleadoEntity> listaEmpleados = tq.getResultList();
		for(EmpleadoEntity e : listaEmpleados) {
			System.out.println(e.getDepartamento().getDptoId() + " - " + e.getNombre() + " - " + e.getSalario());
		}
	}
	
	public void ejercicio8_12() {
		TypedQuery<EmpleadoEntity> tq = em.createQuery("SELECT e FROM EmpleadoEntity e "
				+ "WHERE e.dirId IS NULL", EmpleadoEntity.class);
		List<EmpleadoEntity> listaEmpleados = tq.getResultList();
		for(EmpleadoEntity e : listaEmpleados) {
			System.out.println(e.getEmpnoId() + " - " + e.getNombre());
		}
	}
	
	public void ejercicio8_13() {
		TypedQuery<EmpleadoEntity> tq = em.createQuery("SELECT e FROM EmpleadoEntity e "
				+ "WHERE e.empnoId = 1039", EmpleadoEntity.class);
		EmpleadoEntity e = tq.getSingleResult();
		System.out.println(e.getDepartamento().getDptoId() + " - " + e.getDepartamento().getNombre());
	}
	
	public int incrementarSalario(int cantidad) {
		em.getTransaction().begin();
		Query q = em.createQuery("UPDATE EmpleadoEntity SET salario = salario + " + cantidad);
		int resultado = q.executeUpdate();
		em.getTransaction().commit();
		return resultado;
	}
	
	public int incrementarSalarioOficio(String oficio, int cantidad) {
		em.getTransaction().begin();
		Query q = em.createQuery("UPDATE EmpleadoEntity SET salario = salario + :cant "
				+ "WHERE UPPER(oficio) LIKE UPPER(:ofi)");
		q.setParameter("cant", cantidad);
		q.setParameter("ofi", oficio);
		int resultado = q.executeUpdate();
		em.getTransaction().commit();
		return resultado;
	}
	
	public int incrementarSalarioDepartamento(int numDepartamento, int cantidad) {
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("UPDATE EmpleadoEntity SET salario = salario + :cant "
					+ "WHERE departamento.getDptoId() = :num");
			q.setParameter("cant", cantidad);
			q.setParameter("num", numDepartamento);
			int resultado = q.executeUpdate();
			em.getTransaction().commit();
			return resultado;
			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int borrarEmpleado(int numEmpleado) {
		em.getTransaction().begin();
		try {
			Query q = em.createQuery("DELETE FROM EmpleadoEntity WHERE empnoId = :num");
			q.setParameter("num", numEmpleado);
			int resultado = q.executeUpdate();
			
			Query qu = em.createQuery("UPDATE EmpleadoEntity SET dirId = null WHERE dirId = :num");
			qu.setParameter("num", numEmpleado);
			System.out.println("Empleados modificados: " + qu.executeUpdate());;
			
			em.getTransaction().commit();
			return resultado;
			
		} catch(Exception e) {
			em.getTransaction().rollback();
			System.out.println("EXception");
			e.getStackTrace();
			return 0;
		}
	}
	
	public int borrarDepartamento2(int numDepartamento) {
		em.getTransaction().begin();
		try {
			Query q = em.createQuery("DELETE FROM DepartamentoEntity WHERE dptoId = :num");
			q.setParameter("num", numDepartamento);
			int resultado = q.executeUpdate();
			em.getTransaction().commit();
			return resultado;
			
		} catch(Exception e) {
			em.getTransaction().rollback();
			System.out.println("Exception");
			e.getStackTrace();
			return 0;
		}
	}
	
} // de la clase
