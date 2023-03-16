package com.books.books_store.web;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.books.books_store.model.User;
import com.books.books_store.repository.UserRepository;
import com.books.books_store.web.dto.UserDTO;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserRepository userRepository;

    // Devuelve la lista de usuarios de forma paginada.
    // @param pageable la configuración de paginación que captura los parámetros como: page, size y sort
     
    @GetMapping
    Page<User> paginate(@PageableDefault(sort = "fullName", size = 10) Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    // Devuelve la lista completa de usuarios
    
    @GetMapping("/listar")
    List<User> list() {
        return userRepository.findAll();
    }

    // Devuelve un usuario por su ID.
     
    @GetMapping("/{id}")
    User get(@PathVariable Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    
     // Crea un usuario a partir del cuerpo
   
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    User create(@RequestBody UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return user;
    }

    
     // Actualiza un usuario por su ID
    @PutMapping("/{id}")
    User update(
            @PathVariable Integer id,
            @RequestBody UserDTO userDTO
    ) {
        User user = userRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        modelMapper.map(userDTO, user);

        return user;
    }

    
     //Elimina un usuario por su ID.
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        userRepository.delete(user);
    }

}
