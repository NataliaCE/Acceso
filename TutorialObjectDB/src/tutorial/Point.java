package tutorial;

import java.io.Serializable;
import javax.persistence.*;

@Entity //Significa que el objeto es persistente. Se guarda en disco y no desaparece al cerrar la aplicación.
public class Point implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id //Indica que este atributo es una clase primaria.
	@GeneratedValue //Indica que este atributo se generará de forma automática.
	private long id;

	private int x;
	private int y;

	public Point() {
	}

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Long getId() {
		return id;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", this.x, this.y);
	}
}

