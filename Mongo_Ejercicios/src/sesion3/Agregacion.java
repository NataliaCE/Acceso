package sesion3;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Agregacion {

	public static void main(String[] args) {
		
		//Crear cliente
		MongoClient mongo = new MongoClient("localhost", 27017);
		System.out.println("Conectado a base de datos.");
				
		//Acceder a la base de datos
		MongoDatabase db = mongo.getDatabase("test");
				
		//Acceder a la colección
		MongoCollection<Document> coleccion = db.getCollection("ciudades");
		System.out.println("Colección seleccionada.");
		
		//ej27(coleccion);
		ej28(coleccion);
		
		mongo.close();

	}
	
	//País, población y cantidad de ciudades de dicho país, ordenados de mayor a menor población.
	public static void ej27(MongoCollection<Document> coleccion) {
		
		Document group = new Document("$group", new Document("_id", new Document("pais", "$country"))
				.append("poblacion", new Document("$sum", "$population"))
				.append("ciudades", new Document("$sum", 1)));
		Document sort = new Document("$sort", new Document("poblacion", -1));
		
		List<Document> ciudadesQuery = new ArrayList<>();
		ciudadesQuery.add(group);
		ciudadesQuery.add(sort);
		
		MongoCursor<Document> mongoIterator = coleccion.aggregate(ciudadesQuery).iterator();
		while(mongoIterator.hasNext()) {
			System.out.println(mongoIterator.next());
		}
	}
	
	public static void ej28(MongoCollection<Document> coleccion) {
		
		Document group = new Document("$group", new Document("_id", new Document("pais", "$country"))
				.append("ratio", new Document("$avg", "$population")));
		Document sort = new Document("$sort", new Document("ratio", -1));
		
		List<Document> ciudadesQuery = new ArrayList<>();
		ciudadesQuery.add(group);
		ciudadesQuery.add(sort);
		
		MongoCursor<Document> mongoIterator = coleccion.aggregate(ciudadesQuery).iterator();
		while(mongoIterator.hasNext()) {
			System.out.println(mongoIterator.next());
		}
	}
	
	

}
