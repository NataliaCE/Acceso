package ejemplo1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//
//Alberto Carrera Mart�n - Abril 2020
//
@Entity //Se conserva tras la finalizaci�n de la aplicaci�n
public class DepartamentoEntity {
	@Id //Clave primaria
	private int dptoId;
	private String nombre;
	private String localidad;
	@OneToMany(mappedBy="departamento") // Relaci�n 1 a muchos (un departamento para muchos empleados), en Hibernate --> Empleadoses
		private Set<EmpleadoEntity> empleados = new HashSet<EmpleadoEntity>();
	/*
	 * @OneToMany(mappedBy="departamento",cascade= CascadeType.ALL, orphanRemoval=true)
	 * 
	 * cascadeType.ALL --> Se transmiten en cascada las 5 posibilidades (persist, refresh, etc) 
	 * cascadeType.PERSIST --> Las persistencias hechas en el departamento se propagan a los empleados
	 * cascadeType.REFRESH --> Las actualizaciones hechas en el departamento se propagan a los empleados
	 * cascadeType.REMOVE --> Las eliminaciones hechas en el departamento se propagan a los empleados, que se quedan "zombies"
	 * 
	 * FetchType.EAGER --> Al recuperar un departamento, recupera tambi�n todos sus empleados
	 * FetchType.LAZY --> Al recuperar un departamentos, extrae solo el departamento sin sus empleados
	 */
	
	//
	public DepartamentoEntity(int dptoId, String nombre, String localidad) {

		this.dptoId = dptoId;
		this.nombre = nombre;
		this.localidad = localidad;

	}
	//
	public int getDptoId() {
		return dptoId;
	}
	public void setDptoId(int dptoId) {
		this.dptoId = dptoId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public Set<EmpleadoEntity> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(Set<EmpleadoEntity> empleados) {
		this.empleados = empleados;
	}
	@Override
	public String toString() {
		return "DepartamentoEntity [dptoId=" + dptoId + ", nombre=" + nombre + ", localidad=" + localidad
				+ ", empleados=" + empleados + "]";
	}
	
}
