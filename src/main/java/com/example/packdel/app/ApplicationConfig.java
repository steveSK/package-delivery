package com.example.packdel.app;

import com.example.packdel.io.input.ClosableInputReader;
import com.example.packdel.io.input.InputReader;
import com.example.packdel.io.output.OutputEmitter;

public interface ApplicationConfig {

    ClosableInputReader getPreloadReader();
    InputReader getMainReader();
    OutputEmitter getOutputEmitter();
}
