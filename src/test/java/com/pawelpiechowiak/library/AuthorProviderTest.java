package com.pawelpiechowiak.library;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AuthorProviderTest {

    private Deserializer deserializer;
    private AuthorProvider author;

    @Before
    public void setUp() {
        deserializer = new Deserializer();
        deserializer.readBooksFromJson();
        author = new AuthorProvider(deserializer);
    }

    @Test
    public void segregateAuthors() {
        List<Author> authors = author.segregateAuthors();
        Author firstAuthor = authors.get(0);
        Author lastAuthor = authors.get(authors.size() - 1);

        assertEquals("Jain Pravin", firstAuthor.getName());
        assertEquals("James William Bayley Money", lastAuthor.getName());
    }
}