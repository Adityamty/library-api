package com.example.service;

import com.example.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final Map<Integer, Book> bookStorage = new HashMap<>();

    public List<Book> getAllBooks(Optional<String> author, Optional<Integer> year, int page, int size) {
        return bookStorage.values().stream()
                .filter(b -> author.map(a -> b.getAuthor().equalsIgnoreCase(a)).orElse(true))
                .filter(b -> year.map(y -> b.getPublishedYear() == y).orElse(true))
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    public Optional<Book> getBookById(int id) {
        return Optional.ofNullable(bookStorage.get(id));
    }

    public Book addBook(Book book) {
        bookStorage.put(book.getId(), book);
        return book;
    }

    public Book updateBook(int id, Book book) {
        bookStorage.put(id, book);
        return book;
    }

    public boolean deleteBook(int id) {
        return bookStorage.remove(id) != null;
    }
}