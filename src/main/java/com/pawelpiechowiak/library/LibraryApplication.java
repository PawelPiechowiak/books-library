package com.pawelpiechowiak.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) throws FileNotFoundException {
//        Deserializer object = new Deserializer();
//        object.readFromJson();
//
//        IsbnBook isbn = new IsbnBook();
//        Book book = isbn.findBook(object, "9788131758649");
//        System.out.println(book.getTitle());
        SpringApplication.run(LibraryApplication.class, args);
    }

}
