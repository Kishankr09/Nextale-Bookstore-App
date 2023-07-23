package com.nextory.techtest.controllers;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nextory.techtest.models.Book;
import com.nextory.techtest.models.Comment;
import com.nextory.techtest.services.CommentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/api/addcomment")
    public void submitComment(@RequestParam("bookId") Long bookId,
            @RequestParam("pseudonyme") String pseudonyme,
            @RequestParam("content") String content,
            @RequestParam("rating") int rating,
            HttpServletRequest request,
            HttpServletResponse response)  throws IOException  {
        Book book = new Book();
        book.setId(bookId);
        Comment comment = new Comment();
        comment.setPseudonyme(pseudonyme);
        comment.setContent(content);
        comment.setBook(book);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setRating(rating);
        commentService.addComment(comment);
        response.sendRedirect("/book/" + bookId);
    }
}
