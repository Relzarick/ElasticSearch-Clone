package db;

import org.bson.Document;

import java.util.List;

public interface Repository {
    void fetch();

    void insert(Document doc);

    void batchInsert(List<Document> batch);

    int countDoc();

    void close();
}
