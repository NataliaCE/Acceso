package sesion1;

import java.util.Iterator;
import java.util.TreeSet;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {

	public static void main(String[] args) {
		
		//Crear cliente
		MongoClient mongo = new MongoClient("localhost", 27017);
		System.out.println("Conectado a base de datos.");
		
		//Acceder a la base de datos
		MongoDatabase db = mongo.getDatabase("test");
		
		//Acceder a la colección
		MongoCollection<Document> coleccion = db.getCollection("ciudades");
		System.out.println("Colección seleccionada.");
		
		Ciudad huesca = new Ciudad();
		huesca.setName("Huesca");
		huesca.setCountry("ES");
		huesca.setPopulation((long) 54083);

		//insertaCiudad(huesca, coleccion);
		//listarCiudades(coleccion);
		//listarCiudadesPais("FR", coleccion);
		listarPaises(coleccion);
		
		mongo.close();
	}
	
	public static boolean insertaCiudad(Ciudad ciudad, MongoCollection<Document> coleccion) {
		//Crear documento
		Document documento = new Document("name", ciudad.getName())
				.append("country", ciudad.getCountry())
				.append("timezone", ciudad.getTimezone())
				.append("location", new Document("longitude", ciudad.getLongitude())
						.append("latitude", ciudad.getLatitude()))
				.append("population", ciudad.getPopulation());
		
		//Insertar documento
		try {
			coleccion.insertOne(documento);
			System.out.println("Documento insertado");
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void listarCiudades(MongoCollection<Document> coleccion) {
		//Busqueda
		FindIterable<Document> iterDoc = coleccion.find();
		
		//Iterador
		Iterator<Document> it = iterDoc.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().getString("name"));
		}
	}
	
	public static void listarCiudadesPais(String pais, MongoCollection<Document> coleccion) {
		Document criterios = new Document("country", pais);
		FindIterable<Document> find = coleccion.find(criterios);
		Iterator<Document> it = find.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().getString("name"));
		}
	}
	
	public static void listarPaises(MongoCollection<Document> coleccion) {
		TreeSet<String> paises = new TreeSet<String>();
		
		FindIterable<Document> iterDoc = coleccion.find();
		Iterator<Document> it = iterDoc.iterator();
		while(it.hasNext()) {
			paises.add(it.next().getString("country"));
		}
		
		for(String pais : paises) {
			System.out.println(pais);
		}
	}

}
