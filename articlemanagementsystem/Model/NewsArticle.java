package com.example.articlemanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {
    @NotEmpty(message = "id is empty")
    private String id;
    @NotEmpty(message = "title is empty")
@Size(max=100, message = "title cannot be more than 100 character")
    private String title;
    @NotEmpty(message = "author is empty")
@Size(min = 4,max = 20 ,message = "author must be between 4 and 20 character")
    private String author;
    @NotEmpty(message = "content is empty")
    @Size(min=200, message = "content Must be more than 200 characters.")
    private String content;
    @NotEmpty(message = "category is empty")
@Pattern(regexp = "politics|sports|technology" ,message = "category must match politics or sports or technology ")
    private String category;
    @NotEmpty(message = "imageUrl is empty")
    private String imageUrl;
    private boolean isPublished=false;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;



}
