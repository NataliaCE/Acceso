package anexoII;

import java.sql.Date;

public class Empleado {

	private int numEmpleado;
	private String nombre;
	private String trabajo;
	private int manager;
	private Date fechaContrato;
	private double salario;
	private double comision;
	private int numDepartamento;
	
	public Empleado() {
		
	}

	public Empleado(int numEmpleado, String nombre, String trabajo, int manager, Date fechaContrato, double salario,
			double comision, int numDepartamento) {
		super();
		this.numEmpleado = numEmpleado;
		this.nombre = nombre;
		this.trabajo = trabajo;
		this.manager = manager;
		this.fechaContrato = fechaContrato;
		this.salario = salario;
		this.comision = comision;
		this.numDepartamento = numDepartamento;
	}

	public int getNumEmpleado() {
		return numEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTrabajo() {
		return trabajo;
	}

	public int getManager() {
		return manager;
	}

	public Date getFechaContrato() {
		return fechaContrato;
	}

	public double getSalario() {
		return salario;
	}

	public double getComision() {
		return comision;
	}

	public int getNumDepartamento() {
		return numDepartamento;
	}

	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public void setFechaContrato(Date fechaContrato) {
		this.fechaContrato = fechaContrato;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	public void setNumDepartamento(int numDepartamento) {
		this.numDepartamento = numDepartamento;
	}

	@Override
	public String toString() {
		return "Empleado [numEmpleado=" + numEmpleado + ", nombre=" + nombre + ", trabajo=" + trabajo + ", manager="
				+ manager + ", fechaContrato=" + fechaContrato + ", salario=" + salario + ", comision=" + comision
				+ ", numDepartamento=" + numDepartamento + "]";
	}
	
}
