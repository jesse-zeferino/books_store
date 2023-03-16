package com.books.books_store.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDto {
    @NotNull
    @Size(min = 3,max = 100)
    private String title;

    @NotNull
    @Min(0)
    private Float price;  
    
    @NotNull
    @Pattern(regexp = "[a-z0-9-]+")
    private String slug;

    @NotBlank(message = "La descripcion es obligatoria")
    private String desc;
    
}
