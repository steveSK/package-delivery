package com.example.packdel.storage;

import com.example.packdel.data.Package;
import com.example.packdel.data.PackagesSummary;

import java.util.List;

public interface PackageStorage {

    void addPackage(Package pcg);

    List<Package> getPackagesByPostalNumber(String postalNumber);

    List<PackagesSummary> getPackagesSummary();

}
