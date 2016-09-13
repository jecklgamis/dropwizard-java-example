package com.jecklgamis.dropwizard.example;

import com.google.inject.Injector;
import com.google.inject.Key;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;


final class InjectorHelper {

    public static <T extends Annotation> List<Object> instancesWithAnnotation(Injector injector, Class<T> annotationClass) {
        List<Key<?>> keyList = injector.getAllBindings().keySet().stream()
                .filter(k -> k.getTypeLiteral().getRawType().getAnnotationsByType(annotationClass).length > 0)
                .collect(Collectors.toList());
        return keyList.stream().map(k -> injector.getInstance(k)).collect(Collectors.toList());
    }
}

