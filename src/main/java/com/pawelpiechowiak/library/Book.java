package com.pawelpiechowiak.library;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String id;
    private String title;
    private String subtitle;
    private String publisher;
    private String publishedDate;   //tu musi być na wyjściu long
    private String description;
    private int pageCount;
    private String thumbnail;
    private String language;
    private String previewLink;
    private double averageRating;

    private List<String> authors;
    private List<String> categories;

    public Book(String id, String title, String subtitle, String publisher, String publishedDate, String description, int pageCount, String thumbnail, String language, String previewLink, double averageRating, JsonArray authors, JsonArray categories, JsonArray industryIdentifiers) {
        if (title != null)
            this.title = title;
        if (subtitle != null)
            this.subtitle = subtitle;
        if (publisher != null)
            this.publisher = publisher;
        if (publishedDate != null)
            this.publishedDate = publishedDate;
        if (description != null)
            this.description = description;
        if (Integer.valueOf(pageCount) != null)
            this.pageCount = pageCount;
        if (thumbnail != null)
            this.thumbnail = thumbnail;
        if (language != null)
            this.language = language;
        if (previewLink != null)
            this.previewLink = previewLink;
        if (Double.valueOf(averageRating) != null)
            this.averageRating = averageRating;
        if (authors != null) {
            this.authors = new ArrayList<>();
            for (int i = 0; i < authors.size(); i++) {
                this.authors.add(authors.get(i).getAsString());
            }
        }
        if (categories != null) {
            this.categories = new ArrayList<>();
            for (int i = 0; i < categories.size(); i++) {
                this.categories.add(categories.get(i).getAsString());
            }
        }
        if (industryIdentifiers != null) {
            for (int i = 0; i < industryIdentifiers.size(); i++) {
                String checkISBN = industryIdentifiers.get(i).getAsJsonObject().get("type").getAsString();
                if (checkISBN.equals("ISBN_13")) {
                    this.id = industryIdentifiers.get(i).getAsJsonObject().get("identifier").getAsString();
                } else {
                    this.id = id;
                }
            }
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getLanguage() {
        return language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getCategories() {
        return categories;
    }
}
