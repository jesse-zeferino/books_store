package com.books.books_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.books_store.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
