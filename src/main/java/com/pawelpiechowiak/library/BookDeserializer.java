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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDeserializer {
    private List<Book> books;
    private JsonArray jsonItems;
    private static final String VOLUME_INFO = "volumeInfo";

    public BookDeserializer() {
        this.books = new ArrayList<>();
        try {
            Gson gson = new Gson();
            File file = ResourceUtils.getFile("classpath:static/books.json");
            JsonObject jsonObject = gson.fromJson(new FileReader(file), JsonObject.class);
            jsonItems = jsonObject.getAsJsonArray("items");
        } catch (FileNotFoundException e) {
            System.exit(1);
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
                .withPublisher(getStringObject(object, "publisher"))
                .withSubtitle(getStringObject(object, "subtitle"))
                .withTitle(getStringObject(object, "title"))
                .withAuthors(getStringListObject(object, "authors"))
                .withCategories(getStringListObject(object, "categories"))
                .withAverageRating(getAverageRatingObject(object))
                .withPublishedDate(getPublishedDateObject(object))
                .withPageCount(getPageCountObject(object))
                .withThumbnail(getThumbnailObject(object))
                .withIsbn(getIsbnObject(object, object.get("id").getAsString()))
                .build();
    }

    private String getStringObject(JsonObject object, String name) {
        JsonElement element = object.getAsJsonObject(VOLUME_INFO).get(name);
        if (element != null) {
            return element.getAsString();
        }
        return null;
    }

    private Integer getPageCountObject(JsonObject object) {
        JsonElement element = object.getAsJsonObject(VOLUME_INFO).get("pageCount");
        if (element != null) {
            return element.getAsInt();
        }
        return null;
    }

    private Double getAverageRatingObject(JsonObject object) {
        JsonElement element = object.getAsJsonObject(VOLUME_INFO).get("averageRating");
        if (element != null) {
            return element.getAsDouble();
        }
        return null;
    }

    private Long getPublishedDateObject(JsonObject object) {
        JsonElement element = object.getAsJsonObject(VOLUME_INFO).get("publishedDate");
        if (element != null) {
            String publishedDate = element.getAsString();
            try {
                SimpleDateFormat simpleDateFormat;
                if (publishedDate.length() == 4) {
                    simpleDateFormat = new SimpleDateFormat("yyyy");
                } else {
                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                }
                Date date = simpleDateFormat.parse(publishedDate);
                return date.getTime();
            } catch (ParseException e) {

            }
        }
        return null;
    }

    private String getIsbnObject(JsonObject object, String id) {
        JsonElement element = object.getAsJsonObject(VOLUME_INFO).get("industryIdentifiers");
        String isbn = "";
        if (element != null) {
            JsonArray industryIdentifiers = element.getAsJsonArray();
            for (int i = 0; i < industryIdentifiers.size(); i++) {
                String checkISBN = industryIdentifiers.get(i).getAsJsonObject().get("type").getAsString();
                if (checkISBN.equals("ISBN_13")) {
                    isbn = industryIdentifiers.get(i).getAsJsonObject().get("identifier").getAsString();
                    break;
                } else {
                    isbn = id;
                }
            }
            return isbn;
        }
        return null;
    }

    private String getThumbnailObject(JsonObject object) {
        JsonElement element = object.getAsJsonObject(VOLUME_INFO).get("imageLinks");
        if (element != null) {
            return element.getAsJsonObject().get("thumbnail").getAsString();
        }
        return null;
    }

    private List<String> getStringListObject(JsonObject object, String name) {
        JsonElement element = object.getAsJsonObject(VOLUME_INFO).get(name);
        List<String> list = new ArrayList<>();
        if (element != null) {
            JsonArray array = element.getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                list.add(array.get(i).getAsString());
            }
        }
        return list;
    }
}
