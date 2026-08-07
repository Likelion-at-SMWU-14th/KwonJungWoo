package com.likelion.seminar.controller;

import com.likelion.seminar.dto.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.likelion.seminar.service.BookService;

@Controller
public class BookController {

    BookService bookService = new BookService();

    @PostMapping("/book")
    public String addBook(Book book) {
        bookService.addBooks(book);
        return "redirect:/book";
    }

    @GetMapping("/book")
    public String getBooks(Model model) {
        bookService.getBooks();
        model.addAttribute("book", bookService.getBooks());
        return "book";
    }

}
