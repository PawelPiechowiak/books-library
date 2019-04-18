package com.pawelpiechowiak.library;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DeserializerTest {
    private Deserializer deserializer;

    @Before
    public void setUp() {
        deserializer = new Deserializer();
    }

    @Test
    public void readFromJson() {
        deserializer.readBooksFromJson();
        Book convertedJson = deserializer.getBooks().get(37);

        assertEquals("9780080568782", convertedJson.getIsbn());
        assertEquals("TCP/IP Sockets in Java", convertedJson.getTitle());
        assertEquals("Practical Guide for Programmers", convertedJson.getSubtitle());
        assertEquals("Morgan Kaufmann", convertedJson.getPublisher());
        assertEquals(1314568800000L, convertedJson.getPublishedDate());
        assertEquals("The networking capabilities of the Java platform have been " +
                "extended considerably since the first edition of the book. This new edition covers version 1.5-1.7, the most current iterations," +
                " as well as making the following improvements: The API (application programming interface) reference sections in each chapter," +
                " which describe the relevant parts of each class, have been replaced with (i) a summary section that lists the classes and " +
                "methods used in the code, and (ii) a \"gotchas\" section that mentions nonobvious or poorly-documented aspects of the " +
                "objects. In addition, the book covers several new classes and capabilities introduced in the last few revisions of the Java " +
                "platform. New abstractions to be covered include NetworkInterface, InterfaceAddress, Inet4/6Address, " +
                "SocketAddress/InetSocketAddress, Executor, and others; extended access to low-level network information; support " +
                "for IPv6; more complete access to socket options; and scalable I/O. The example code is also modified to take advantage of " +
                "new language features such as annotations, enumerations, as well as generics and implicit iterators where appropriate. Most " +
                "Internet applications use sockets to implement network communication protocols. This book's focused, tutorial-based approach " +
                "helps the reader master the tasks and techniques essential to virtually all client-server projects using sockets in Java. " +
                "Chapter 1 provides a general overview of networking concepts to allow readers to synchronize the concepts with terminology. " +
                "Chapter 2 introduces the mechanics of simple clients and servers. Chapter 3 covers basic message construction and parsing. " +
                "Chapter 4 then deals with techniques used to build more robust clients and servers. Chapter 5 (NEW) introduces the scalable " +
                "interface facilities which were introduced in Java 1.5, including the buffer and channel abstractions. Chapter 6 discusses the" +
                " relationship between the programming constructs " +
                "and the underlying protocol implementations in more detail. Programming concepts are introduced " +
                "through simple program examples accompanied by line-by-line code commentary that describes the purpose " +
                "of every part of the program. No other resource presents so concisely or so effectively the material necessary " +
                "to get up and running with Java sockets programming. Focused, tutorial-based instruction " +
                "in key sockets programming techniques allows reader to quickly come up to speed on Java applications. " +
                        "Concise and up-to-date coverage of the most recent platform (1.7) for Java applications in networking technology."
                , convertedJson.getDescription());
        assertEquals(192, convertedJson.getPageCount());
        assertEquals("http://books.google.com/books/content?id=lfHo7uMk7r4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api", convertedJson.getThumbnailUrl());
        assertEquals("en", convertedJson.getLanguage());
        assertEquals("http://books.google.pl/books?id=lfHo7uMk7r4C&printsec=frontcover&dq=java&hl=&cd=38&source=gbs_api", convertedJson.getPreviewLink());
        assertEquals(4.0, convertedJson.getAverageRating(), 1);
        assertEquals(new ArrayList<String>() {{
            add("Kenneth L. Calvert");
            add("Michael J. Donahoo");
        }}, convertedJson.getAuthors());
        assertEquals(new ArrayList<String>() {{
            add("Computers");
        }}, convertedJson.getCategories());
    }

    @Test
    public void convertJsonToBookWithAllFields() {
        Book convertedJson = deserializer.convertJsonToBook(deserializer.getJsonItems().get(37).getAsJsonObject());

        assertEquals("9780080568782", convertedJson.getIsbn());
        assertEquals("TCP/IP Sockets in Java", convertedJson.getTitle());
        assertEquals("Practical Guide for Programmers", convertedJson.getSubtitle());
        assertEquals("Morgan Kaufmann", convertedJson.getPublisher());
        assertEquals(1314568800000L, convertedJson.getPublishedDate());
        assertEquals("The networking capabilities of the Java platform have been " +
                        "extended considerably since the first edition of the book. This new edition covers version 1.5-1.7, the most current iterations," +
                        " as well as making the following improvements: The API (application programming interface) reference sections in each chapter," +
                        " which describe the relevant parts of each class, have been replaced with (i) a summary section that lists the classes and " +
                        "methods used in the code, and (ii) a \"gotchas\" section that mentions nonobvious or poorly-documented aspects of the " +
                        "objects. In addition, the book covers several new classes and capabilities introduced in the last few revisions of the Java " +
                        "platform. New abstractions to be covered include NetworkInterface, InterfaceAddress, Inet4/6Address, " +
                        "SocketAddress/InetSocketAddress, Executor, and others; extended access to low-level network information; support " +
                        "for IPv6; more complete access to socket options; and scalable I/O. The example code is also modified to take advantage of " +
                        "new language features such as annotations, enumerations, as well as generics and implicit iterators where appropriate. Most " +
                        "Internet applications use sockets to implement network communication protocols. This book's focused, tutorial-based approach " +
                        "helps the reader master the tasks and techniques essential to virtually all client-server projects using sockets in Java. " +
                        "Chapter 1 provides a general overview of networking concepts to allow readers to synchronize the concepts with terminology. " +
                        "Chapter 2 introduces the mechanics of simple clients and servers. Chapter 3 covers basic message construction and parsing. " +
                        "Chapter 4 then deals with techniques used to build more robust clients and servers. Chapter 5 (NEW) introduces the scalable " +
                        "interface facilities which were introduced in Java 1.5, including the buffer and channel abstractions. Chapter 6 discusses the" +
                        " relationship between the programming constructs " +
                        "and the underlying protocol implementations in more detail. Programming concepts are introduced " +
                        "through simple program examples accompanied by line-by-line code commentary that describes the purpose " +
                        "of every part of the program. No other resource presents so concisely or so effectively the material necessary " +
                        "to get up and running with Java sockets programming. Focused, tutorial-based instruction " +
                        "in key sockets programming techniques allows reader to quickly come up to speed on Java applications. " +
                        "Concise and up-to-date coverage of the most recent platform (1.7) for Java applications in networking technology."
                , convertedJson.getDescription());
        assertEquals(192, convertedJson.getPageCount());
        assertEquals("http://books.google.com/books/content?id=lfHo7uMk7r4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api", convertedJson.getThumbnailUrl());
        assertEquals("en", convertedJson.getLanguage());
        assertEquals("http://books.google.pl/books?id=lfHo7uMk7r4C&printsec=frontcover&dq=java&hl=&cd=38&source=gbs_api", convertedJson.getPreviewLink());
        assertEquals(4.0, convertedJson.getAverageRating(), 1);
        assertEquals(new ArrayList<String>() {{
            add("Kenneth L. Calvert");
            add("Michael J. Donahoo");
        }}, convertedJson.getAuthors());
        assertEquals(new ArrayList<String>() {{
            add("Computers");
        }}, convertedJson.getCategories());
    }

    @Test
    public void convertJsonToBookWithFieldsOfNull() {
        Book convertedJson = deserializer.convertJsonToBook(deserializer.getJsonItems().get(32).getAsJsonObject());
        assertNull(convertedJson.getSubtitle());
        assertNull(convertedJson.getDescription());
    }

    @Test
    public void convertJsonToBookWithoutIsbn() {
        Book convertedJson = deserializer.convertJsonToBook(deserializer.getJsonItems().get(11).getAsJsonObject());
        assertEquals("N1IiAQAAIAAJ", convertedJson.getIsbn());
    }
}