package com.nextory.techtest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.nextory.techtest.models.Book;
import com.nextory.techtest.repositories.BookRepository;

@Service
public class BookService {
    
    @Autowired
    BookRepository bookRepository;


    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();
        bookRepository.findAll().forEach(book -> books.add(book));

        return (books);
    }

    public Page<Book> getPaginatedBooks(int page, int pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, getAllBooks().size());

        List<Book> booksOnPage = getAllBooks().subList(startIndex, endIndex);

        return new PageImpl<>(booksOnPage, PageRequest.of(page - 1, pageSize), getAllBooks().size());
    }

    public Book getBookById(Integer bookId) {
        return (bookRepository.findById(bookId).get());
    }

    public List<Book> getTopSuggetions(Book book , int suggesionSize)
    {
        List<Book> top5Books = new ArrayList<Book>();

        List<Book> books = getAllBooks();

        for (Book b : books) {
            if (book.getAuthor().equals(b.getAuthor()) && !book.equals(b)) 
            {
                if(top5Books.size() < suggesionSize)
                top5Books.add(b);
                else break;
            }
        }
        if(top5Books.size() < 5) 
        {      
            books.sort((Book b1, Book b2) -> b2.getBoostScore().compareTo(b1.getBoostScore()));

            for (Book b : books) {
                if (!top5Books.contains(b) && !book.equals(b)) 
                {
                    if(top5Books.size() < suggesionSize)
                    top5Books.add(b);
                    else break;
                }
            }
            
        }

        return top5Books;


    }

}
