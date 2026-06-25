package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public final class Database implements Repository {
    public Database() {
//        client = MongoClients.create("mongodb://mongrel:27017");
        client = MongoClients.create("mongodb://localhost:27017"); // Used for testing (Run db service first)
        db = client.getDatabase("mongrel-db");

        System.out.println("Database initialized");
    }

    @Override
    public void fetch() {
        MongoCollection<Document> col = db.getCollection("col");
    }

    @Override
    public void insert() {
        MongoCollection<Document> col = db.getCollection("col");
//        col.insertOne();

    }

    @Override
    public void batchInsert() {
        MongoCollection<Document> col = db.getCollection("col");
//        col.insertMany();
    }

    @Override
    public void close() {
        if (client != null)
            client.close();
    }

    private final MongoClient client;
    private final MongoDatabase db;

}