package oneToOne;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Estudiante {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Eid;
	private String Enombre;
	@OneToOne
	private Notas notas;
	
	public Estudiante(String enombre) {
		Enombre = enombre;
	}
	
	public int getEid() {
		return Eid;
	}
	public String getEnombre() {
		return Enombre;
	}
	public Notas getNotas() {
		return notas;
	}
	public void setEid(int eid) {
		Eid = eid;
	}
	public void setEnombre(String enombre) {
		Enombre = enombre;
	}
	public void setNotas(Notas notas) {
		this.notas = notas;
	}
	
	//https://www.adictosaltrabajo.com/2020/04/02/hibernate-onetoone-onetomany-manytoone-y-manytomany/
	//https://www.tutorialspoint.com/es/jpa/jpa_entity_relationships.htm
	
}
