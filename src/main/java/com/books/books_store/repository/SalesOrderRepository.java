package com.books.books_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.books_store.model.SalesOrder;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Integer> {
    
}
