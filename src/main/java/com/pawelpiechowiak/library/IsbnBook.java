package com.pawelpiechowiak.library;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

@RestController
public class IsbnBook {
    private static final Deserializer book = new Deserializer();

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @RequestMapping(value = "/books/{isbn}", method = RequestMethod.GET)
    @ResponseBody
    public String getBook(@PathVariable("isbn") String isbn) throws FileNotFoundException, ParseException {
        book.readFromJson();
        if (findBookByISBN(isbn) != null) {
            Gson gson = new Gson();
            return gson.toJson(findBookByISBN(isbn));
        } else {
            throw new ResourceNotFoundException("The book does not exist.");
        }
    }

    private Book findBookByISBN(String isbn) {
        List<Book> books = book.getBooks();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}
