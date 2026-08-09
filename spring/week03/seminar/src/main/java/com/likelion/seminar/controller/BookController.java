package com.likelion.seminar.controller;

import com.likelion.seminar.dto.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.likelion.seminar.service.BookService;

import java.util.List;

// @Controller
@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

//    @PostMapping("/book")
//    public String addBook(Book book) {
//        bookService.addBooks(book);
//        return "redirect:/book";
//    }

//    @GetMapping("/book")
//    public String getBooks(Model model) {
//        bookService.getBooks();
//        model.addAttribute("book", bookService.getBooks());
//        return "book";
//    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBooks(book);
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

}
