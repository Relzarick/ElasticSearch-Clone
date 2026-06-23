package bootstrap;

import org.apache.commons.csv.CSVFormat;
import etl.CsvParser;

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

        new CsvParser(format, csvfile[0]);
    }

    public static class AppSetupException extends Exception {
        private AppSetupException(String message) {
            super(message);
        }
    }

}

// need shutdown hook to close db on JVM exit??