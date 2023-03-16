package com.books.books_store.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.books.books_store.model.Book;
import com.books.books_store.repository.BookRepository;
import com.books.books_store.web.dto.BookDto;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/list")
    List<Book> list(){
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
     Book get(@PathVariable Integer id) {
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping("/search")
    List <Book> search (@RequestParam String q){
        return bookRepository.findAll().stream().filter(l->l.getTitle().toLowerCase().contains(q.toLowerCase())).collect(Collectors.toList());
    }

@ResponseStatus(HttpStatus.CREATED )
@PostMapping
Book create(@Validated @RequestBody BookDto bookDto){
    ModelMapper  mapper= new ModelMapper();
    Book book= new Book();
    mapper.map(bookDto, book);
    return bookRepository.save(book);
}

@PutMapping("/{id}")
Book update( @PathVariable Integer id, @RequestBody BookDto bookDto){
   
 Book book= bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
 
   new ModelMapper().map(bookDto, book);
 bookRepository.save(book);
 return book;
}

@ResponseStatus(HttpStatus.NO_CONTENT )
@DeleteMapping("/{id}")
void delete( @PathVariable Integer id){
    Book book= bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    bookRepository.delete(book);

}
  @GetMapping
    Page<Book> paginate(@PageableDefault(sort = "title", direction=Sort.Direction.ASC, size = 10) Pageable pageable){
        return bookRepository.findAll(pageable);
    }

}
