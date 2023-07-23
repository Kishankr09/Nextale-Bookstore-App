package com.nextory.techtest.views;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.nextory.techtest.models.Book;
import com.nextory.techtest.services.BookService;

@Controller
public class BookViewController {

    @Autowired
    BookService bookService;

    int pageSizes[] = {25, 50, 100,-1};

    @GetMapping("/book")
    public String getBookPage(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "25") int pageSize, Model model) {
        Page<Book> bookPage = null;
        if(pageSize == -1)
        {
            bookPage = bookService.getPaginatedBooks(page, Integer.MAX_VALUE);
        }
        else
        {
            bookPage = bookService.getPaginatedBooks(page, pageSize);
        }
        
        List<Book> books = bookPage.getContent();
        model.addAttribute("books", books);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("selectedPageSize", pageSize);
        model.addAttribute("pageSizes", pageSizes);

        return ("books/index");
    }

    @GetMapping("/book/{id}")
    public String getBookDetailPage(@PathVariable("id") Integer _id, Model model,@RequestParam(defaultValue = "5") int suggesionSize) {
        Book book = bookService.getBookById(_id);
        model.addAttribute("book", book);
        List<Book> books = bookService.getTopSuggetions(book, suggesionSize);
        model.addAttribute("books", books);
        return ("books/detail");
    }
}