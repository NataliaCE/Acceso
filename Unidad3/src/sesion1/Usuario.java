//EJERCICIO 2

package sesion1;

public class Usuario {
	
	private String username;
	private String password;
	private String nombre;
	
	public Usuario(String username, String password, String nombre) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
