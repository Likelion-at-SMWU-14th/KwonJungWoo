package com.likelion.seminar.service;

import com.likelion.seminar.dto.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    public void addBooks(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }
}
