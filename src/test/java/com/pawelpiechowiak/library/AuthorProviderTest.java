package com.pawelpiechowiak.library;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AuthorProviderTest {

    private BookDeserializer bookDeserializer;
    private AuthorProvider authorProvider;

    @Before
    public void setUp() {
        bookDeserializer = new BookDeserializer();
        bookDeserializer.readBooksFromJson();
        authorProvider = new AuthorProvider(bookDeserializer);
    }

    @Test
    public void sortAuthors() {
        List<Author> authors = authorProvider.getSortedAuthors();
        Author firstAuthor = authors.get(0);
        Author lastAuthor = authors.get(authors.size() - 1);

        assertEquals("Jain Pravin", firstAuthor.getName());
        assertEquals("James William Bayley Money", lastAuthor.getName());
    }
}