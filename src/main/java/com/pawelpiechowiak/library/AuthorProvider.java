package com.pawelpiechowiak.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuthorProvider {
    private Deserializer deserializer;
    private List<DuplicatedAuthor> duplicatedAuthors;

    public AuthorProvider(Deserializer deserializer) {
        this.deserializer = deserializer;
        this.duplicatedAuthors = new ArrayList<>();
    }

    private class DuplicatedAuthor {
        private String name;
        private Double rating;
        private double n;

        public DuplicatedAuthor(String name, Double rating) {
            this.name = name;
            this.rating = rating;
            this.n = 0;
        }

        public String getName() {
            return name;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public double getN() {
            return n;
        }

        public void setN(double n) {
            this.n = n;
        }
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
                    duplicatedAuthors.add(new DuplicatedAuthor(author.getName(), author.getAverageRating()));
                }
            }
        }
    }

    private DuplicatedAuthor findByName(String name, List<DuplicatedAuthor> authors) {
        for (DuplicatedAuthor author : authors) {
            if (author.getName().equals(name)) {
                return author;
            }
        }
        return null;
    }

    private List<Author> convertAuthors() {
        getAuthorsFromBook();
        List<DuplicatedAuthor> myAuthors = new ArrayList<>();
        List<Author> listToSend = new ArrayList<>();

        for (DuplicatedAuthor author : duplicatedAuthors) {
            DuplicatedAuthor object = findByName(author.getName(), myAuthors);
            if (object != null) {
                object.setRating((object.getRating() * object.getN() + author.getRating()) / (object.getN() + 1.0));
                object.setN(object.getN() + 1.0);
            } else {
                author.setN(1);
                myAuthors.add(author);
            }
        }
        for (DuplicatedAuthor x : myAuthors) {
            System.out.println(x.getName());
            listToSend.add(new Author(x.getName(), x.getRating()));
        }
        return listToSend;
    }

    public List<Author> segregateAuthors() {
        List<Author> authors = convertAuthors();
        Collections.sort(authors);
        return authors;
    }
}
