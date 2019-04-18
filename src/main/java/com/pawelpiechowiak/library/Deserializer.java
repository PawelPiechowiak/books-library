package com.pawelpiechowiak.library;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Deserializer {
    private List<Book> books;
    private JsonArray jsonItems;

    public Deserializer() {
        this.books = new ArrayList<>();
        try {
            Gson gson = new Gson();
            File file = ResourceUtils.getFile("classpath:static/books.json");
            JsonObject jsonObject = gson.fromJson(new FileReader(file), JsonObject.class);
            jsonItems = jsonObject.getAsJsonArray("items");
        } catch (FileNotFoundException e) {

        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public JsonArray getJsonItems() {
        return jsonItems;
    }

    public void readBooksFromJson() {
        for (int n = 0; n < jsonItems.size(); n++) {
            JsonObject object = jsonItems.get(n).getAsJsonObject();
            books.add(convertJsonToBook(object));
        }
    }

    public Book convertJsonToBook(JsonObject object) {

        return new BookBuilder().withDescription(getStringObject(object, "description"))
                .withLanguage(getStringObject(object, "language"))
                .withPreviewLink(getStringObject(object, "previewLink"))
                .withPublishedDate(getStringObject(object, "publishedDate"))
                .withPublisher(getStringObject(object, "publisher"))
                .withSubtitle(getStringObject(object, "subtitle"))
                .withTitle(getStringObject(object, "title"))
                .withAuthors(getJsonArrayObject(object, "authors"))
                .withAverageRating(getDoubleObject(object, "averageRating"))
                .withCategories(getJsonArrayObject(object, "categories"))
                .withPageCount(getIntObject(object, "pageCount"))
                .withThumbnail(getThumbnailObject(object, "imageLinks"))
                .withIsbn(object.get("id").getAsString(), getJsonArrayObject(object, "industryIdentifiers"))
                .build();
    }

    private String getStringObject(JsonObject object, String name) {
        JsonElement element = object.getAsJsonObject("volumeInfo").get(name);
        if (element != null) {
            return element.getAsString();
        }
        return null;
    }

    private Integer getIntObject(JsonObject object, String name) {
        JsonElement element = object.getAsJsonObject("volumeInfo").get(name);
        if (element != null) {
            return element.getAsInt();
        }
        return null;
    }

    private Double getDoubleObject(JsonObject object, String name) {
        JsonElement element = object.getAsJsonObject("volumeInfo").get(name);
        if (element != null) {
            return element.getAsDouble();
        }
        return null;
    }

    private JsonArray getJsonArrayObject(JsonObject object, String name) {
        JsonElement element = object.getAsJsonObject("volumeInfo").get(name);
        if (element != null) {
            return element.getAsJsonArray();
        }
        return null;
    }

    private String getThumbnailObject(JsonObject object, String name) {
        JsonElement element = object.getAsJsonObject("volumeInfo").get(name);
        if (element != null) {
            return element.getAsJsonObject().get("thumbnail").getAsString();
        }
        return null;
    }
}
