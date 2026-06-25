package bootstrap;

import db.Database;
import db.Repository;
import etl.CsvParser;
import org.apache.commons.csv.CSVFormat;

import java.io.File;


// Run this to innit database and index
public final class AppSetup {
    public AppSetup() throws AppSetupException {
        CSVFormat format = CSVFormat.EXCEL.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(true)
                .get();

        String pathName = "data";

        File dataDir = new File(pathName);
        File[] csvfile = dataDir.listFiles((dir, name) -> name.endsWith(".csv"));

        if (csvfile == null)
            throw new AppSetupException("Data dir was not found");

        if (csvfile.length == 0)
            throw new AppSetupException("No .csv file found in " + pathName);

        CsvParser parser = new CsvParser(csvfile[0], format);
        Repository db = new Database();

    }

    public static class AppSetupException extends Exception {
        private AppSetupException(String message) {
            super(message);
        }
    }

}

// csv returns xx and pass to db
// need shutdown hook to close db on JVM exit (return after fully built)