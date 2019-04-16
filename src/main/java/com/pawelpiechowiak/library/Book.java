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

    public void setPublishedDate(String publishedDate) throws ParseException {
        if (publishedDate != null) {
            SimpleDateFormat simpleDateFormat;
            if (publishedDate.length() == 4) {
                simpleDateFormat = new SimpleDateFormat("yyyy");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            }
            Date date = simpleDateFormat.parse(publishedDate);
            this.publishedDate = date.getTime();
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

    public void setAuthors(JsonArray authors) {
        if (authors != null) {
            this.authors = new ArrayList<>();
            for (int i = 0; i < authors.size(); i++) {
                this.authors.add(authors.get(i).getAsString());
            }
        }
    }

    public void setCategories(JsonArray categories) {
        if (categories != null) {
            this.categories = new ArrayList<>();
            for (int i = 0; i < categories.size(); i++) {
                this.categories.add(categories.get(i).getAsString());
            }
        }
    }

    public void setId(String id, JsonArray industryIdentifiers) {
        if (industryIdentifiers != null) {
            for (int i = 0; i < industryIdentifiers.size(); i++) {
                String checkISBN = industryIdentifiers.get(i).getAsJsonObject().get("type").getAsString();
                if (checkISBN.equals("ISBN_13")) {
                    this.isbn = industryIdentifiers.get(i).getAsJsonObject().get("identifier").getAsString();
                    break;
                } else {
                    this.isbn = id;
                }
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

    public Long getPublishedDate() {
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
