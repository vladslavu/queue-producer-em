package ro.dsrl.queueproducer.sender;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class CsvReader {

    @Value("${energy.management.csv-file-path}")
    private String filePath;

    public List<Float> readAllValues() {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            return reader.readAll()
                    .stream()
                    .map(arrayOfStrings -> Float.parseFloat(arrayOfStrings[0]))
                    .collect(Collectors.toList());
        }
        catch (IOException exception) {
            log.error(exception.getMessage());
        }
        catch (CsvException csvException) {
            log.error("There was an error while reading from CSV file.");
        }
        return null;
    }
}
