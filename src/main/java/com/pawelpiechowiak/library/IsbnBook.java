package com.pawelpiechowiak.library;

import java.util.List;

public class IsbnBook {

    public Book findBook(Deserializer booksList, String isbn) {
        List<Book> books = booksList.getBooks();

        for (Book book : books) {
//            System.out.println(book.getId());
            if (book.getId().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}
