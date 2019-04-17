package com.pawelpiechowiak.library;

import com.google.gson.JsonArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
    private String isbn;
    private String title;
    private String subtitle;
    private String publisher;
    private Long publishedDate;
    private String description;
    private Integer pageCount;
    private String thumbnail;
    private String language;
    private String previewLink;
    private Double averageRating;

    private List<String> authors;
    private List<String> categories;

    public Book() {
    }

    public Book(String isbn, String title, String subtitle, String publisher, Long publishedDate, String description, Integer pageCount, String thumbnail, String language, String previewLink, Double averageRating, List<String> authors, List<String> categories) {
        this.isbn = isbn;
        this.title = title;
        this.subtitle = subtitle;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = pageCount;
        this.thumbnail = thumbnail;
        this.language = language;
        this.previewLink = previewLink;
        this.averageRating = averageRating;
        this.authors = authors;
        this.categories = categories;
    }

    public void setTitle(String title) {
        if (title != null)
            this.title = title;
    }

    public void setSubtitle(String subtitle) {
        if (subtitle != null)
            this.subtitle = subtitle;
    }

    public void setPublisher(String publisher) {
        if (publisher != null)
            this.publisher = publisher;
    }

    public void setPublishedDate(Long publishedDate) {
        if (publishedDate != null) {
            this.publishedDate = publishedDate;
        }
    }

    public void convertPublishedDate(String publishedDate) {
        try {
            SimpleDateFormat simpleDateFormat;
            if (publishedDate.length() == 4) {
                simpleDateFormat = new SimpleDateFormat("yyyy");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            }
            Date date = simpleDateFormat.parse(publishedDate);
            setPublishedDate(date.getTime());
        } catch (ParseException e) {
            System.out.println("ParseException occurred. PublishedDate = null");
        }
    }

    public void setDescription(String description) {
        if (description != null)
            this.description = description;
    }

    public void setPageCount(Integer pageCount) {
        if (pageCount != null) {
            this.pageCount = pageCount;
        }
    }

    public void setThumbnail(String thumbnail) {
        if (thumbnail != null)
            this.thumbnail = thumbnail;
    }

    public void setLanguage(String language) {
        if (language != null)
            this.language = language;
    }

    public void setPreviewLink(String previewLink) {
        if (previewLink != null)
            this.previewLink = previewLink;
    }

    public void setAverageRating(Double averageRating) {
        if (averageRating != null)
            this.averageRating = averageRating;
    }

    public void setAuthors(List<String> authors) {
        if (authors != null) {
            this.authors = authors;
        }
    }

    public void convertAuthors(JsonArray authors) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < authors.size(); i++) {
            list.add(authors.get(i).getAsString());
        }
        setAuthors(list);
    }

    public void setCategories(List<String> categories) {
        if (categories != null) {
            this.categories = categories;
        }
    }

    public void convertCategories(JsonArray categories) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            list.add(categories.get(i).getAsString());
        }
        setCategories(list);
    }

    public void setIsbn(String isbn) {
        if (isbn != null) {
            this.isbn = isbn;
        }
    }

    public void convertIndustryIdentifiers(String id, JsonArray industryIdentifiers) {
        for (int i = 0; i < industryIdentifiers.size(); i++) {
            String checkISBN = industryIdentifiers.get(i).getAsJsonObject().get("type").getAsString();
            if (checkISBN.equals("ISBN_13")) {
                setIsbn(industryIdentifiers.get(i).getAsJsonObject().get("identifier").getAsString());
                break;
            } else {
                setIsbn(id);
            }
        }
    }

    public String getIsbn() {
        return isbn;
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

    public long getPublishedDate() {
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
