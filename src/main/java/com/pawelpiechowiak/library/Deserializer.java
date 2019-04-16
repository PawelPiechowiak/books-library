package com.pawelpiechowiak.library;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Deserializer {
    private List<Book> books;

    public Deserializer() {
        this.books = new ArrayList<>();
    }

    private JsonElement isVolumeNull(JsonArray array, int index, String name) {
        JsonObject object = array.get(index).getAsJsonObject();
        JsonElement element = object.getAsJsonObject("volumeInfo").get(name);
        if (element != null) {
            return element;
        }
        return null;
    }

    public void readFromJson() throws FileNotFoundException, ParseException {
        Gson gson = new Gson();
        File file = ResourceUtils.getFile("classpath:static/books.json");
        JsonObject jsonObject = gson.fromJson(new FileReader(file), JsonObject.class);
        JsonArray items = jsonObject.getAsJsonArray("items");

        for (int n = 0; n < items.size(); n++) {
            Book book = new Book();
            JsonObject object = items.get(n).getAsJsonObject();

            if (isVolumeNull(items, n, "title") != null)
                book.setTitle(isVolumeNull(items, n, "title").getAsString());
            if (isVolumeNull(items, n, "subtitle") != null)
                book.setSubtitle(isVolumeNull(items, n, "subtitle").getAsString());
            if (isVolumeNull(items, n, "publisher") != null)
                book.setPublisher(isVolumeNull(items, n, "publisher").getAsString());
            if (isVolumeNull(items, n, "publishedDate") != null)
                book.setPublishedDate(isVolumeNull(items, n, "publishedDate").getAsString());
            if (isVolumeNull(items, n, "description") != null)
                book.setDescription(isVolumeNull(items, n, "description").getAsString());
            if (isVolumeNull(items, n, "pageCount") != null)
                book.setPageCount(isVolumeNull(items, n, "pageCount").getAsInt());
            if (isVolumeNull(items, n, "imageLinks") != null)
                book.setThumbnail(isVolumeNull(items, n, "imageLinks").getAsJsonObject().get("thumbnail").getAsString());
            if (isVolumeNull(items, n, "language") != null)
                book.setLanguage(isVolumeNull(items, n, "language").getAsString());
            if (isVolumeNull(items, n, "previewLink") != null)
                book.setPreviewLink(isVolumeNull(items, n, "previewLink").getAsString());
            if (isVolumeNull(items, n, "averageRating") != null)
                book.setAverageRating(isVolumeNull(items, n, "averageRating").getAsDouble());
            if (isVolumeNull(items, n, "authors") != null)
                book.setAuthors(isVolumeNull(items, n, "authors").getAsJsonArray());
            if (isVolumeNull(items, n, "categories") != null)
                book.setCategories(isVolumeNull(items, n, "categories").getAsJsonArray());
            if (isVolumeNull(items, n, "industryIdentifiers") != null)
                book.setId(object.get("id").getAsString(), isVolumeNull(items, n, "industryIdentifiers").getAsJsonArray());

            books.add(book);
        }
    }

    public List<Book> getBooks() {
        return books;
    }
}
