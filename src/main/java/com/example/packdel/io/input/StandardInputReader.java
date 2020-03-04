package com.example.packdel.io.input;

import com.example.packdel.exception.InputException;
import com.example.packdel.exception.InputParseException;
import com.example.packdel.storage.PackageStorage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class StandardInputReader implements ClosableInputReader {

    private final static String QUIT_COMMAND = "quit";
    private final BufferedReader reader;
    private final InputParser inputParser;
    private final PackageStorage packageStorage;
    private Boolean closed = false;

    public StandardInputReader(PackageStorage packageStorage, InputParser inputParser) {
        this.packageStorage = packageStorage;
        this.inputParser = inputParser;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void read() {

        while (!closed) {
            try {
                String line = reader.readLine();
                if (StringUtils.isNotEmpty(line)) {
                    if (line.equals(QUIT_COMMAND)) {
                        close();
                        break;
                    } else {
                        packageStorage.addPackage(inputParser.parsePackage(line));
                    }
                }
            } catch (InputParseException e) {
                System.out.println("Input Parse error: " + e.getMessage());
            } catch (IOException e) {
                log.error("Problem reading input: " + e.getMessage(), e.getCause());
                throw new InputException(e.getMessage(), e);
            }
        }

    }

    @Override
    public void close() {
        if (!isClosed()) {
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
        return closed;
    }
}
