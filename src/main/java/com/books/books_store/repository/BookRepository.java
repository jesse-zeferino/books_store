package com.books.books_store.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.books.books_store.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    
}
