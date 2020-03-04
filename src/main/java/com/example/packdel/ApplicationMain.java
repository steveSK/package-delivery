package com.example.packdel;

import com.example.packdel.app.ApplicationFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationMain {

    public static void main(String[] args) {
        Logger.getLogger("org").setLevel(Level.SEVERE);

        System.out.println("WELCOME IN PACKAGE DELIVERY APP:");
        System.out.println("-------------------------------------------------");
        ApplicationFactory.defaultApp(args).start();
    }


}
