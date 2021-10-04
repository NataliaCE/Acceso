package ejercicios;

import java.io.Serializable;

public class Profesor implements Serializable{
	String nombre;
	int antiguedad;
	
	public Profesor(String nombre, int antiguedad) {
		this.nombre = nombre;
		this.antiguedad = antiguedad;
	}

	@Override
	public String toString() {
		return "Profesor [nombre=" + nombre + ", antiguedad=" + antiguedad + "]";
	}
	

}
