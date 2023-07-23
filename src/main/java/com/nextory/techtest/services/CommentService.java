package com.nextory.techtest.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nextory.techtest.models.Comment;
import com.nextory.techtest.repositories.CommentRepository;

@Service
public class CommentService {
    
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();
        commentRepository.findAll().forEach(comment -> comments.add(comment));
        
        comments.sort((Comment c1, Comment c2) -> Integer.compare(c2.getRating(), c1.getRating()));
        for(Comment ce : comments ){
            System.out.println(ce.getRating());
        }
        return (comments);
    }
    
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}
