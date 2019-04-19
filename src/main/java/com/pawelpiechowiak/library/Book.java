package com.pawelpiechowiak.library;

import java.util.List;

public class Book {
    private String isbn;
    private String title;
    private String subtitle;
    private String publisher;
    private Long publishedDate;
    private String description;
    private Integer pageCount;
    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private Double averageRating;

    private List<String> authors;
    private List<String> categories;

    public void setTitle(String title) {
            this.title = title;
    }

    public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
    }

    public void setPublisher(String publisher) {
            this.publisher = publisher;
    }

    public void setPublishedDate(Long publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setDescription(String description) {
            this.description = description;
    }

    public void setPageCount(Integer pageCount) {
            this.pageCount = pageCount;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
    }

    public void setLanguage(String language) {
            this.language = language;
    }

    public void setPreviewLink(String previewLink) {
            this.previewLink = previewLink;
    }

    public void setAverageRating(Double averageRating) {
            this.averageRating = averageRating;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getLanguage() {
        return language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getCategories() {
        return categories;
    }
}
