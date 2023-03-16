package com.books.books_store.web.dto;

import com.books.books_store.model.User;

import lombok.Data;
@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private User.Role role;
}
