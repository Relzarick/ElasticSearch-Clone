package etl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class CsvParser {
    public CsvParser(File file) {
        this.file = file;
        this.format = CSVFormat.EXCEL.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(true)
                .get();
    }

    public void parse(Consumer<List<Document>> consumer) {
        try (CSVParser parser = CSVParser.parse(file, StandardCharsets.UTF_8, format)
        ) {
            String[] headerNames = parser.getHeaderNames().toArray(new String[0]);
            List<Document> batch = new ArrayList<>();

            for (CSVRecord record : parser) {
                Document doc = new Document();

                for (String header : headerNames) {
                    doc.append(header, record.get(header));
                }

                batch.add(doc);

                if (batch.size() == 1000) {
                    consumer.accept(batch);
                    batch.clear();
                }
            }
//
            if (!batch.isEmpty())
                consumer.accept(batch);

        } catch (IOException e) {
            System.out.println("ERROR with parsing: " + e.getMessage());
        }

    }

    File file;
    CSVFormat format;
}

// if saving it, have to make sure this gets gc