package bookrecommender.src;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csvMaker {


    private FileFinder fileFinder;

    public csvMaker() {
        fileFinder = new FileFinder();
        String inputFile = String.valueOf(fileFinder.MasterCSVPath());
        String outputFile = String.valueOf(fileFinder.LibrifilePath());


        try (
                CSVReader reader = new CSVReader(new FileReader(inputFile));
                CSVWriter writer = new CSVWriter(new FileWriter(outputFile));
        ) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                nextLine = fixMalformedLine(reader, nextLine);

                List<String> selectedColumns = new ArrayList<>();
                selectedColumns.add(nextLine[0]);
                selectedColumns.add(nextLine[1]);
                selectedColumns.add(nextLine[7]);



                writer.writeNext(selectedColumns.toArray(new String[0]));
            }
        } catch (IOException | CsvValidationException ex) {
            ex.printStackTrace();
        }
    }

    private String[] fixMalformedLine(CSVReader reader, String[] line) throws IOException, CsvValidationException {
        for (int i = 0; i < line.length; i++) {
            if (line[i].startsWith("\"") && !line[i].endsWith("\"")) {
                StringBuilder fixedField = new StringBuilder(line[i]);
                String[] nextLine;

                while ((nextLine = reader.readNext()) != null) {
                    fixedField.append("\n").append(nextLine[i]);
                    if (nextLine[i].endsWith("\"")) {
                        line[i] = fixedField.toString();
                        break;
                    }
                }
            }
        }
        return line;
    }
}