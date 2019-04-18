package com.pawelpiechowiak.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuthorProvider {
    private Deserializer deserializer;
    private List<Author> duplicatedAuthors;

    public AuthorProvider(Deserializer deserializer) {
        this.deserializer = deserializer;
        this.duplicatedAuthors = new ArrayList<>();
    }

    private void getAuthorsFromBook() {
        List<Book> books = deserializer.getBooks();
        for (Book book : books) {
            if (book.getAuthors() != null && book.getAverageRating() != null) {
                List<String> authorsFromBook = book.getAuthors();
                for (String authorFromBook : authorsFromBook) {
                    Author author = new Author();
                    author.setName(authorFromBook);
                    author.setAverageRating(book.getAverageRating());
                    author.setBooksValue(1);
                    duplicatedAuthors.add(author);
                }
            }
        }
    }

    private List<Author> convertAuthors() {
        getAuthorsFromBook();
        List<Author> myAuthors = new ArrayList<>();

        for (Author author : duplicatedAuthors) {
            Author object = findByName(author.getName(), myAuthors);
            if (object != null) {
                object.setAverageRating(
                        (object.getAverageRating() * object.getBooksValue() + author.getAverageRating()) / (object.getBooksValue() + 1)
                );
                object.setBooksValue(object.getBooksValue() + 1);
            } else {
                myAuthors.add(author);
            }
        }
        for (Author author : myAuthors) {
            author.setBooksValue(null);
        }
        return myAuthors;
    }

    private Author findByName(String name, List<Author> authors) {
        for (Author author : authors) {
            if (author.getName().equals(name)) {
                return author;
            }
        }
        return null;
    }

    public List<Author> sortAuthors() {
        List<Author> authors = convertAuthors();
        Collections.sort(authors);
        return authors;
    }
}
