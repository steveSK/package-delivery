package com.example.packdel.storage;


import com.example.packdel.data.Package;
import com.example.packdel.data.PackagesSummary;
import com.example.packdel.exception.PackageNotValidException;
import com.example.packdel.util.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class HashMapPackageStorage implements PackageStorage {

    private Map<String, List<Package>> storage = new HashMap<>();

    @Override
    public synchronized void addPackage(Package pcg) {
        log.info("Added package: " + pcg + " to storage");
        validatePackage(pcg);
        List<Package> postPackages = storage.get(pcg.getPostalCode());
        if (postPackages == null) {
            storage.put(pcg.getPostalCode(), List.of(pcg));
        } else {
            storage.put(pcg.getPostalCode(), ListUtil.addUnmodifiable(postPackages, pcg));
        }
    }

    @Override
    public synchronized List<Package> getPackagesByPostalNumber(String postalNumber) {
        return storage.getOrDefault(postalNumber, List.of());
    }

    @Override
    public synchronized List<PackagesSummary> getPackagesSummary() {
        return storage.entrySet().stream().map(keypair -> new PackagesSummary(keypair.getKey(),
                sumWeight(keypair.getValue()))).collect(Collectors.toList());
    }


    private BigDecimal sumWeight(List<Package> packages) {
        return packages.stream().map(pcg -> pcg.getWeight()).reduce((x, y) -> x.add(y)).orElseThrow();
    }

    private void validatePackage(Package pcg) {
        if (pcg == null) {
            throw new PackageNotValidException("package is null");
        }
        if (StringUtils.isEmpty(pcg.getPostalCode())) {
            throw new PackageNotValidException("package postal code is empyt");
        }
        if (pcg.getWeight() == null) {
            throw new PackageNotValidException("package weight is null");
        }

    }


}
