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
            System.out.println("File not found. System is closing..");
            System.exit(1);
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public JsonArray getJsonItems() {
        return jsonItems;
    }

    public void readFromJson() {
        for (int n = 0; n < jsonItems.size(); n++) {
            JsonObject object = jsonItems.get(n).getAsJsonObject();
            books.add(convertJsonToBook(object));
        }
    }

    public Book convertJsonToBook(JsonObject object) {
        Book bookToDeserialize = new Book();
        if (isVolumeNull(object, "title") != null)
            bookToDeserialize.setTitle(isVolumeNull(object, "title").getAsString());
        if (isVolumeNull(object, "subtitle") != null)
            bookToDeserialize.setSubtitle(isVolumeNull(object, "subtitle").getAsString());
        if (isVolumeNull(object, "publisher") != null)
            bookToDeserialize.setPublisher(isVolumeNull(object, "publisher").getAsString());
        if (isVolumeNull(object, "publishedDate") != null)
            bookToDeserialize.convertPublishedDate(isVolumeNull(object, "publishedDate").getAsString());
        if (isVolumeNull(object, "description") != null)
            bookToDeserialize.setDescription(isVolumeNull(object, "description").getAsString());
        if (isVolumeNull(object, "pageCount") != null)
            bookToDeserialize.setPageCount(isVolumeNull(object, "pageCount").getAsInt());
        if (isVolumeNull(object, "imageLinks") != null)
            bookToDeserialize.setThumbnail(isVolumeNull(object, "imageLinks").getAsJsonObject().get("thumbnail").getAsString());
        if (isVolumeNull(object, "language") != null)
            bookToDeserialize.setLanguage(isVolumeNull(object, "language").getAsString());
        if (isVolumeNull(object, "previewLink") != null)
            bookToDeserialize.setPreviewLink(isVolumeNull(object, "previewLink").getAsString());
        if (isVolumeNull(object, "averageRating") != null)
            bookToDeserialize.setAverageRating(isVolumeNull(object, "averageRating").getAsDouble());
        if (isVolumeNull(object, "authors") != null)
            bookToDeserialize.convertAuthors(isVolumeNull(object, "authors").getAsJsonArray());
        if (isVolumeNull(object, "categories") != null)
            bookToDeserialize.convertCategories(isVolumeNull(object, "categories").getAsJsonArray());
        if (isVolumeNull(object, "industryIdentifiers") != null)
            bookToDeserialize.convertIndustryIdentifiers(object.get("id").getAsString(), isVolumeNull(object, "industryIdentifiers").getAsJsonArray());

        return bookToDeserialize;
    }

    private JsonElement isVolumeNull(JsonObject object, String name) {
        JsonElement element = object.getAsJsonObject("volumeInfo").get(name);
        if (element != null) {
            return element;
        }
        return null;
    }
}
