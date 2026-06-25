package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.List;

public final class Database implements Repository {
    public Database() {
        client = MongoClients.create("mongodb://mongrel:27017");
//        client = MongoClients.create("mongodb://localhost:27017"); // Used for testing (Run db service first)
        db = client.getDatabase("mongrel-db");

        System.out.println("Database initialized");
    }

    @Override
    public void fetch() {
        MongoCollection<Document> col = db.getCollection("col");
        col.find(Filters.eq("id", 2));
    }

    @Override
    public void insert(Document doc) {
        MongoCollection<Document> col = db.getCollection("col");
        col.insertOne(doc);
    }

    @Override
    public void batchInsert(List<Document> batch) {
        MongoCollection<Document> col = db.getCollection("col");
        col.insertMany(batch);
    }

    @Override
    public int countDoc() {
        MongoCollection<Document> col = db.getCollection("col");
        return Math.toIntExact(col.estimatedDocumentCount());
    }

    @Override
    public void close() {
        if (client != null)
            client.close();
    }

    private final MongoClient client;
    private final MongoDatabase db;
}