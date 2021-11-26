
public class Main {

	public static void main(String[] args) {
		
		View vista = new View();
		Controlador controlador = new Controlador(vista);
		vista.conectaControlador(controlador);
	}

}
