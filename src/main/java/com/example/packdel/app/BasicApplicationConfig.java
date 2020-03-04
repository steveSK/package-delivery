package com.example.packdel.app;

import com.example.packdel.io.input.*;
import com.example.packdel.io.output.OutputEmitter;
import com.example.packdel.io.output.OutputWriter;
import com.example.packdel.io.output.StandardOutputWriter;
import com.example.packdel.storage.HashMapPackageStorage;
import com.example.packdel.storage.PackageStorage;

public class BasicApplicationConfig implements ApplicationConfig {

    private final PackageStorage packageStorage = new HashMapPackageStorage();
    private final InputParser inputParser = new InputParser();
    private final OutputWriter outputWriter = new StandardOutputWriter();

    private final ClosableInputReader preloadReader;
    private final InputReader mainReader;
    private final OutputEmitter outputEmitter;

    public BasicApplicationConfig(String[] args) {

        Integer emmitInterval = 60;
        String fileSource = null;
        if (args.length == 1) {
            fileSource = args[0];
        }

        ClosableInputReader preloadReader = null;
        if (fileSource != null) {
            preloadReader = new FileInputReader(packageStorage, inputParser, fileSource);
        }

        InputReader mainReader = new StandardInputReader(packageStorage, inputParser);
        OutputEmitter outputEmitter = new OutputEmitter(packageStorage, outputWriter, emmitInterval);

        this.preloadReader = preloadReader;
        this.mainReader = mainReader;
        this.outputEmitter = outputEmitter;
    }

    @Override
    public ClosableInputReader getPreloadReader() {
        return preloadReader;
    }

    @Override
    public InputReader getMainReader() {
        return mainReader;
    }

    @Override
    public OutputEmitter getOutputEmitter() {
        return outputEmitter;
    }
}
