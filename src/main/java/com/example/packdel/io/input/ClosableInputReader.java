package com.example.packdel.io.input;

public interface ClosableInputReader extends InputReader {

    void close();
    boolean isClosed();
}
