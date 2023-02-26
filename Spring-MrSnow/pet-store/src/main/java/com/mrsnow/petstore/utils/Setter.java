package com.mrsnow.petstore.utils;

@FunctionalInterface
public interface Setter {

    <T> void setter(T data);
}
