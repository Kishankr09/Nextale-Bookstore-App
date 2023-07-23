package com.nextory.techtest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nextory.techtest.models.Author;
import com.nextory.techtest.repositories.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository; 

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<Author>();
        authorRepository.findAll().forEach(author -> authors.add(author));
        
        return (authors);
    }

    public Page<Author> getPaginatedAuthors(int page, int pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, getAllAuthors().size());

        List<Author> authorsOnPage = getAllAuthors().subList(startIndex, endIndex);

        return new PageImpl<>(authorsOnPage, PageRequest.of(page - 1, pageSize), getAllAuthors().size());
    }
    public Author getAuthorById(Integer authorId) {
        return (authorRepository.findById(authorId).get());
    }
}
