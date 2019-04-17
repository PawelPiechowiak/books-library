package com.pawelpiechowiak.library;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BookProviderTest {
    private Deserializer deserializer;
    private BookProvider bookProvider;

    @Before
    public void setUp() {
        deserializer = new Deserializer();
        deserializer.readBooksFromJson();
        bookProvider = new BookProvider(deserializer);
    }

    @Test
    public void findBookByISBN() {
        Book testBook = bookProvider.findBookByISBN("9781575211534");

        assertEquals(testBook.getIsbn(), "9781575211534");
        assertEquals(testBook.getPublishedDate(), 820450800000L);
        assertNull(testBook.getSubtitle());
    }

    @Test
    public void findBookByCategoryWithCapitalLetter() {
        List<Book> bookList = bookProvider.findBookByCategory("Java");

        assertEquals(bookList.get(0).getIsbn(), "9788324677658");
        assertEquals(bookList.get(1).getIsbn(), "9780131002876");
    }

    @Test
    public void findBookByCategoryWithLowerLetter() {
        List<Book> bookList = bookProvider.findBookByCategory("java");

        assertEquals(bookList.get(0).getIsbn(), "9788324677658");
        assertEquals(bookList.get(1).getIsbn(), "9780131002876");
    }
}