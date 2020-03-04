package com.example.packdel;

import com.example.packdel.data.Package;
import com.example.packdel.data.PackagesSummary;
import com.example.packdel.storage.HashMapPackageStorage;
import com.example.packdel.storage.PackageStorage;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class HashMapPackageStorageTest {

    private final List<Package> testPackages1 = List.of(
            new Package("13000", BigDecimal.valueOf(3.5)),
            new Package("12000", BigDecimal.valueOf(2.7)),
            new Package("11000", BigDecimal.valueOf(2.3))
    );

    private final List<Package> testPackages2 = List.of(
            new Package("13000", BigDecimal.valueOf(3.5)),
            new Package("13000", BigDecimal.valueOf(2.7)),
            new Package("13000", BigDecimal.valueOf(2.3))
    );

    private final List<PackagesSummary> expectedPackagesSummary = List.of(
            new PackagesSummary("13000", BigDecimal.valueOf(8.5))
    );


    private final PackageStorage postalStorage = new HashMapPackageStorage();


    @Test
    public void testAddPackage() {
        testPackages1.stream().forEach(postalStorage::addPackage);
        Assert.assertEquals(testPackages1.get(0), postalStorage.getPackagesByPostalNumber("13000").get(0));
        Assert.assertEquals(testPackages1.get(1), postalStorage.getPackagesByPostalNumber("12000").get(0));
        Assert.assertEquals(testPackages1.get(2), postalStorage.getPackagesByPostalNumber("11000").get(0));
    }


    @Test
    public void testGetPackageByPostalNumber() {
        postalStorage.addPackage(testPackages1.get(0));
        Assert.assertEquals(testPackages1.get(0), postalStorage.getPackagesByPostalNumber("13000").get(0));
    }

    @Test
    public void testGetPackageSummary() {
        testPackages2.stream().forEach(postalStorage::addPackage);
        Assert.assertEquals(expectedPackagesSummary, postalStorage.getPackagesSummary());
    }

}
