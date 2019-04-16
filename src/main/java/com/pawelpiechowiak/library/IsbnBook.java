package com.pawelpiechowiak.library;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
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
    public String getBook(@PathVariable("isbn") String isbn) throws FileNotFoundException {
        book.readFromJson();
        if (findBook(book, isbn) != null) {
            Gson gson = new Gson();
            String json = gson.toJson(findBook(book, isbn));
            return json;
        } else {
            throw new ResourceNotFoundException("The book does not exist.");
        }
    }

    private Book findBook(Deserializer booksList, String isbn) {
        List<Book> books = booksList.getBooks();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}
