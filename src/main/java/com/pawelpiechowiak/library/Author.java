package com.pawelpiechowiak.library;

public class Author implements Comparable<Author> {
    private String name;
    private double averageRating;

    public Author() {
    }

    public Author(String name, double averageRating) {
        this.name = name;
        this.averageRating = averageRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public int compareTo(Author author) {
        if (this.averageRating > author.averageRating) {
            return -1;
        } else if (this.averageRating < author.averageRating) {
            return 1;
        } else {
            return 0;
        }
    }
}
