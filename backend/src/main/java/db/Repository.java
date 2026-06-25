package db;

public interface Repository {
    void fetch();

    void insert();

    void batchInsert();

    void close();
}
