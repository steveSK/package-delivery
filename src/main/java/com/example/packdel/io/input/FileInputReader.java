package com.example.packdel.io.input;

import com.example.packdel.exception.FileNotFoundException;
import com.example.packdel.exception.InputException;
import com.example.packdel.storage.PackageStorage;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class FileInputReader implements ClosableInputReader {

    private final String filePath;
    private final InputParser inputParser;
    private final PackageStorage packageStorage;
    private final BufferedReader reader;
    private Boolean closed = false;


    public FileInputReader(PackageStorage packageStorage, InputParser inputParser, String filePath) {
        this.packageStorage = packageStorage;
        this.inputParser = inputParser;
        this.filePath = filePath;
        try {
            this.reader = new BufferedReader(new FileReader(filePath));
        } catch (java.io.FileNotFoundException e) {
            log.error("There were an error processing file input" + e.getMessage(), e);
            throw new FileNotFoundException(filePath);
        }
    }

    public void read() {

        System.out.println("Preloading data from file: " + filePath);
        String inputLine;
        try {
            while ((inputLine = reader.readLine()) != null) {
                packageStorage.addPackage(inputParser.parsePackage(inputLine));
            }
        } catch (IOException e) {
            log.error("There were an error processing file input" + e.getMessage(), e);
            throw new InputException(e.getMessage(), e);
        }
    }


    @Override
    public void close() {
        if(!isClosed()) {
            try {
                reader.close();
                closed = true;
            } catch (IOException e) {
                log.error("There were an error in closing standard input", e);
                throw new InputException(e.getMessage(), e);
            }
        }
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
