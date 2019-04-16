package com.pawelpiechowiak.library;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    private Deserializer deserializer = new Deserializer();
    private BookProvider book = new BookProvider(deserializer);

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @RequestMapping(value = "/books/{isbn}", method = RequestMethod.GET)
    @ResponseBody
    public String getBook(@PathVariable("isbn") String isbn) {
        deserializer.readFromJson();

        if (book.findBookByISBN(isbn) != null) {
            Gson gson = new Gson();
            return gson.toJson(book.findBookByISBN(isbn));
        } else {
            throw new ResourceNotFoundException("The book does not exist.");
        }
    }
}
