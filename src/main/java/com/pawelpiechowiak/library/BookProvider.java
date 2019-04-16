package com.pawelpiechowiak.library;

import java.util.List;

public class BookProvider {
    private Deserializer book;

    public BookProvider(Deserializer book) {
        this.book = book;
    }

    public Book findBookByISBN(String isbn) {
        List<Book> books = book.getBooks();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}
