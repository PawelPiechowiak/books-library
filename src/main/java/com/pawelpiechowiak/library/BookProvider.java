package com.pawelpiechowiak.library;

import java.util.ArrayList;
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

    public List<Book> findBookByCategory(String category) {
        List<Book> bookList = book.getBooks();
        List<Book> booksToShow = new ArrayList<>();
        for (Book bookFromList : bookList) {
            if (bookFromList.getCategories() != null && category != null) {
                for (String bookCategory : bookFromList.getCategories()) {
                    if (bookCategory.toLowerCase().equals(category.toLowerCase())) {
                        booksToShow.add(bookFromList);
                    }
                }
            }
        }
        return booksToShow;
    }
}
