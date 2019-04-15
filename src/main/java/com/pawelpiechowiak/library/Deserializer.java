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
    private JsonArray authors;
    private JsonArray categories;
    private JsonArray industryIdentifiers;

    public Deserializer() {
        this.books = new ArrayList<>();
    }

    public JsonElement isVolumeNull(JsonArray array, int index, String name) {
        JsonObject object = array.get(index).getAsJsonObject();
        JsonElement element = object.getAsJsonObject("volumeInfo").get(name);
        if (element != null) {
            return element;
        }
        return null;
    }

    public void readFromJson() throws FileNotFoundException {
        Gson gson = new Gson();
        File file = ResourceUtils.getFile("classpath:static/books.json");
        JsonObject jsonObject = gson.fromJson(new FileReader(file), JsonObject.class);
        JsonArray items = jsonObject.getAsJsonArray("items");

        for (int n = 0; n < items.size(); n++) {
            JsonObject object = items.get(n).getAsJsonObject();

            if (isVolumeNull(items, n, "title") != null)
                title = isVolumeNull(items, n, "title").getAsString();
            if (isVolumeNull(items, n, "subtitle") != null)
                subtitle = isVolumeNull(items, n, "subtitle").getAsString();
            if (isVolumeNull(items, n, "publisher") != null)
                publisher = isVolumeNull(items, n, "publisher").getAsString();
            if (isVolumeNull(items, n, "publishedDate") != null)
                publishedDate = isVolumeNull(items, n, "publishedDate").getAsString();
            if (isVolumeNull(items, n, "description") != null)
                description = isVolumeNull(items, n, "description").getAsString();
            if (isVolumeNull(items, n, "pageCount") != null)
                pageCount = isVolumeNull(items, n, "pageCount").getAsInt();
            if (isVolumeNull(items, n, "thumbnail") != null)
                thumbnail = isVolumeNull(items, n, "imageLinks").getAsJsonObject().get("thumbnail").getAsString();
            if (isVolumeNull(items, n, "language") != null)
                language = isVolumeNull(items, n, "language").getAsString();
            if (isVolumeNull(items, n, "previewLink") != null)
                previewLink = isVolumeNull(items, n, "previewLink").getAsString();
            if (isVolumeNull(items, n, "averageRating") != null)
                averageRating = isVolumeNull(items, n, "averageRating").getAsDouble();
            if (isVolumeNull(items, n, "authors") != null)
                authors = isVolumeNull(items, n, "authors").getAsJsonArray();
            if (isVolumeNull(items, n, "categories") != null)
                categories = isVolumeNull(items, n, "categories").getAsJsonArray();
            if (isVolumeNull(items, n, "industryIdentifiers") != null)
                industryIdentifiers = isVolumeNull(items, n, "industryIdentifiers").getAsJsonArray();

            Book data = new Book(object.get("id").getAsString(), title, subtitle, publisher, publishedDate, description,
                    pageCount, thumbnail, language, previewLink, averageRating, authors, categories, industryIdentifiers);
            books.add(data);
        }

        for (Book x : books) {
            System.out.println(x.getPageCount());
        }
    }
}
