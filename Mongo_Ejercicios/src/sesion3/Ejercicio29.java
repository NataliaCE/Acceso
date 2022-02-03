package sesion3;


import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Ejercicio29 {

	private static MongoClient mongo;
	private static MongoDatabase database;
	private static MongoCollection<Document> collection;
	
	public static void main(String[] args) {
		
		mongo = new MongoClient("localhost", 27017);
		database = mongo.getDatabase("test");
		collection = database.getCollection("ciudades");
		
		//3 ciudades españolas con más habitantes
		Document match = new Document("$match", new Document("country", "ES"));
		Document project = new Document("$project", new Document("_id", 0)
				.append("country", 0)
				.append("timezone", 0)
				.append("location", 0)
				.append("tags", 0));
		Document sort = new Document("$sort", new Document("population", -1));
		Document limit = new Document("$limit", 3);
		
		List<Document> ciudadesQuery = new ArrayList<>();
		ciudadesQuery.add(match);
		ciudadesQuery.add(project);
		ciudadesQuery.add(sort);
		ciudadesQuery.add(limit);
		
		MongoCursor<Document> mongoIterator = collection.aggregate(ciudadesQuery).iterator();
		while(mongoIterator.hasNext()) {
			System.out.println(mongoIterator.next());
		}

	}

}
