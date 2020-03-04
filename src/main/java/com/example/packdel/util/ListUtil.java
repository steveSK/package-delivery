package com.example.packdel.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class ListUtil {

    public static <T> List<T> addUnmodifiable(List<T> list, T el){
        return concat(list, List.of(el));
    }

    public static <T> List<T> concat(List<T>... lists) {
        return Stream.of(lists).flatMap(List::stream).collect(Collectors.toList());
    }

}
