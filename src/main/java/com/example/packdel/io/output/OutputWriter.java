package com.example.packdel.io.output;

import com.example.packdel.data.PackagesSummary;

import java.util.List;

public interface OutputWriter {

    void write(List<PackagesSummary> packagesSummary);
    void close();


}
