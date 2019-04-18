package com.pawelpiechowiak.library;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    private Deserializer deserializer = new Deserializer();
    private BookProvider book = new BookProvider(deserializer);

    public BookController() {
        deserializer.readBooksFromJson();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @GetMapping(value = "/books/{isbn}")
    @ResponseBody
    public String getBook(@PathVariable("isbn") String isbn) {
        if (book.findBookByISBN(isbn) != null) {
            Gson gson = new Gson();
            return gson.toJson(book.findBookByISBN(isbn));
        } else {
            throw new ResourceNotFoundException("The book does not exist.");
        }
    }

    @GetMapping(value = "/books/category/{category}")
    @ResponseBody
    public String getCategory(@PathVariable("category") String category) {
        Gson gson = new Gson();
        return gson.toJson(book.findBookByCategory(category));
    }

    @GetMapping(value = "/rating")
    @ResponseBody
    public String getRating() {
        AuthorProvider author = new AuthorProvider(deserializer);
        Gson gson = new Gson();
        return gson.toJson(author.segregateAuthors());
    }
}
