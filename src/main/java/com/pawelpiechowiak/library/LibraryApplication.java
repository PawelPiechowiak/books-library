package com.pawelpiechowiak.library;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) throws FileNotFoundException {
        Deserializer book = new Deserializer();
        book.readFromJson();
        //SpringApplication.run(LibraryApplication.class, args);
    }

}
