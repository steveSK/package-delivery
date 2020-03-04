package com.example.packdel.data;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class PackagesSummary {

    private final String postalCode;
    private final BigDecimal totalWeight;

    @Override
    public String toString() {
        return postalCode + " " + totalWeight.toString();
    }


}
