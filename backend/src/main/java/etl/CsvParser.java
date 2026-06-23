package etl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.charset.StandardCharsets;

public final class CsvParser {
    public CsvParser(CSVFormat format, File file) {
        try (CSVParser csvParser = CSVParser.parse(file, StandardCharsets.UTF_8, format)
        ) {
            headerNames = csvParser.getHeaderNames().toArray(new String[0]);

//            CsvToDB(csvParser);

        } catch (IOException e) {
            System.out.println("ERROR with resource: " + e.getMessage());
        }

    }

    private void CsvToDB(CSVParser parser) {
        for (CSVRecord record : parser) {
            for (String header : headerNames) {
                return;
            }
        }

    }

    private String[] headerNames;

}

// now work on mongo