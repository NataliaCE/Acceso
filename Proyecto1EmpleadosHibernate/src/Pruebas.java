import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primero.Departamentos;
import primero.Empleados;
import primero.SessionFactoryUtil;

public class Pruebas {

	public static void main(String[] args) throws ParseException {
		
		//String fecha = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
		//Date date1 = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
	}

}
