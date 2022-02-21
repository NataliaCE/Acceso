package modelo;

public class Libro {

	private int id;
	private String titulo;
	private String autor;
	private boolean prestado;
	
	public Libro(int id, String titulo, String autor, boolean prestado) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.prestado = prestado;
	}
	
	public int getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getAutor() {
		return autor;
	}
	public boolean isPrestado() {
		return prestado;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}
	
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", prestado=" + prestado + "]";
	}
	
	
	
}
