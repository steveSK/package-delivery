package com.example.packdel.app;

public class ApplicationFactory {

    public static PackageDeliveryApplication defaultApp(String[] args){
        return new PackageDeliveryApplication(new BasicApplicationConfig(args));
    }
}
