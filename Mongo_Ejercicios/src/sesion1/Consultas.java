package sesion1;

import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

public class Consultas {

	public static void main(String[] args) {
		
		//Crear cliente
				MongoClient mongo = new MongoClient("localhost", 27017);
				System.out.println("Conectado a base de datos.");
				
				//Acceder a la base de datos
				MongoDatabase db = mongo.getDatabase("test");
				
				//Acceder a la colección
				MongoCollection<Document> coleccion = db.getCollection("ciudades");
				System.out.println("Colección seleccionada.");
				
				ej5(coleccion);
				
				mongo.close();

	}
	
	// Datos de las ciudades españolas con más de 1.000.000 de habitantes ordenado de mayor a menor.
	public static void ej5(MongoCollection<Document> coleccion) {
		
		Document criterios = new Document("population", new Document("$gte", 10000))
				.append("country", "ES");
		
		//Busqueda
		FindIterable<Document> find = coleccion.find(criterios)
				.sort(new Document("population", -1))
				//.limit(10)
				.projection(Projections.exclude("_id"))
				.projection(Projections.include("name", "population"));
		//Recorrer resultado
		Iterator<Document> it = find.iterator();
		while(it.hasNext()) {
			//Document doc = it.next();
			//System.out.println(doc.getString("name") + " " + doc.getInteger("population"));
			System.out.println(it.next());
		}
		
	}

}
