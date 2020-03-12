package com.example.packdel.io.output;

import com.example.packdel.storage.PackageStorage;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OutputEmitter {

    private static final Integer POOL_SIZE = 1;
    private static final Integer INITIAL_DELAY = 30;

    private final PackageStorage packageStorage;
    private final OutputWriter outputWriter;
    private final ScheduledExecutorService executorService;
    private final Integer emmitInterval;


    public OutputEmitter(PackageStorage packageStorage, OutputWriter outputWriter, Integer emmitInterval) {
        this.packageStorage = packageStorage;
        this.outputWriter = outputWriter;
        this.emmitInterval = emmitInterval;
        executorService = Executors.newScheduledThreadPool(POOL_SIZE);
    }


    public void submit() {
        executorService.scheduleAtFixedRate(new WriterThread(packageStorage, outputWriter), INITIAL_DELAY, emmitInterval, TimeUnit.SECONDS);
    }


    public void shutdown(){
        outputWriter.close();
        executorService.shutdownNow();
    }


    private static class WriterThread implements Runnable {

        private final PackageStorage packageStorage;
        private final OutputWriter outputWriter;

        @Override
        public void run() {
            outputWriter.write(packageStorage.getPackagesSummary());
        }

        public WriterThread(PackageStorage packageStorage, OutputWriter outputWriter) {
            this.packageStorage = packageStorage;
            this.outputWriter = outputWriter;


        }
    }


}
