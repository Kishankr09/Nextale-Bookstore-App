package com.nextory.techtest.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.nextory.techtest.models.Author;
import com.nextory.techtest.services.AuthorService;

@Controller
public class AuthorViewController {
    
    @Autowired
    AuthorService authorService;

    int pageSizes[] = {25, 50, 100, -1};

    @GetMapping("/author")
    public String getAuthorPage(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "25") int pageSize, Model model) {
                Page<Author> authorPage = null;
        System.out.println("page: " + page);
        if(pageSize == -1)
        {
            authorPage = authorService.getPaginatedAuthors(page, Integer.MAX_VALUE);
        }
        else
        {
            authorPage = authorService.getPaginatedAuthors(page, pageSize);
        }
        
        List<Author> authors = authorPage.getContent();
        model.addAttribute("authors", authors);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", authorPage.getTotalPages());
        model.addAttribute("selectedPageSize", pageSize);
        model.addAttribute("pageSizes", pageSizes);

        return ("authors/index");
    }

    @GetMapping("/author/{id}")
    public String getAuthorPageDetail(@PathVariable("id") Integer _id, Model model) {
        Author author = authorService.getAuthorById(_id);
        model.addAttribute("author", author);

        return ("authors/detail");
    }
}
