package com.example.packdel.app;

import com.example.packdel.io.input.ClosableInputReader;
import com.example.packdel.io.input.InputReader;
import com.example.packdel.io.output.OutputEmitter;


public class PackageDeliveryApplication {


    private ClosableInputReader preloadReader;
    private InputReader mainReader;
    private OutputEmitter outputEmitter;

    public PackageDeliveryApplication(ApplicationConfig applicationConfig) {
        this.preloadReader = applicationConfig.getPreloadReader();
        this.mainReader = applicationConfig.getMainReader();
        this.outputEmitter = applicationConfig.getOutputEmitter();
    }


    public void start() {
        // first preload data
        if (preloadReader != null) {
            preloadReader.read();
            preloadReader.close();
        }
        // start emitter thread
        outputEmitter.submit();
        // start read from main reader
        mainReader.read();
        //when main reader is interrupted shutdown emitter thread
        outputEmitter.shutdown();
    }

}
