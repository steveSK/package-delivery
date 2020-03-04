package com.example.packdel.io.output;

import com.example.packdel.data.PackagesSummary;
import com.example.packdel.exception.OutputException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@Slf4j
public class StandardOutputWriter implements OutputWriter {

    private BufferedWriter bufferedWriter;


    public StandardOutputWriter() {
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    @Override
    public void write(List<PackagesSummary> packagesSummary) {
        System.out.println("Packages summary: ");
        packagesSummary.stream().forEach(this::writeSingleLine);
    }

    @Override
    public void close() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            log.error("There were an error in closing standard output: " + e.getMessage(),e);
            new OutputException(e.getMessage(),e);
        }
    }


    private void writeSingleLine(PackagesSummary packagesSummary) {
        try {
            bufferedWriter.write(packagesSummary.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            log.error("There were an error in writing to the standard output: " + e.getMessage(),e);
            throw new OutputException(e.getMessage(), e);
        }
    }
}
