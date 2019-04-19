package com.pawelpiechowiak.library;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BookProviderTest {
    private BookDeserializer bookDeserializer;
    private BookProvider bookProvider;

    @Before
    public void setUp() {
        bookDeserializer = new BookDeserializer();
        bookDeserializer.readBooksFromJson();
        bookProvider = new BookProvider(bookDeserializer);
    }

    @Test
    public void findBookByISBNWithExistingISBN() {
        Book testBook = bookProvider.findBookByISBN("9781575211534");

        assertEquals("9781575211534", testBook.getIsbn());
        assertEquals(820450800000L, testBook.getPublishedDate());
        assertNull(testBook.getSubtitle());
    }

    @Test
    public void findBookByISBNWithoutExistingISBN() {
        Book testBook = bookProvider.findBookByISBN("N1IiAQAAIAAJ");
        assertEquals("N1IiAQAAIAAJ", testBook.getIsbn());
    }

    @Test
    public void findBookByCategoryWithCapitalLetter() {
        List<Book> bookList = bookProvider.findBookByCategory("Java");

        assertEquals("9788324677658", bookList.get(0).getIsbn());
        assertEquals("9780131002876", bookList.get(1).getIsbn());
    }

    @Test
    public void findBookByCategoryWithLowerLetter() {
        List<Book> bookList = bookProvider.findBookByCategory("java");

        assertEquals("9788324677658", bookList.get(0).getIsbn());
        assertEquals("9780131002876", bookList.get(1).getIsbn());
    }
}