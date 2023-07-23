package com.nextory.techtest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nextory.techtest.models.Book;


@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    
}
