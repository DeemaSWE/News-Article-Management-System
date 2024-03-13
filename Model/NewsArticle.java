package com.example.newsarticlemanagementsystem.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class NewsArticle {

    @NotEmpty(message = "Id cannot be empty")
    private String id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(max = 100, message = "Title must be at most 100 characters")
    private String title;

    @NotEmpty(message = "Author cannot be empty")
    @Size(min = 4, max = 20, message = "Author must be between 4 and 20 characters")
    private String author;

    @NotEmpty(message = "Content cannot be empty")
    @Size(min = 200, message = "Content must be at least 200 characters")
    private String content;

    @NotEmpty(message = "Category cannot be empty")
    @Pattern(regexp = "^(politics|sports|technology)$", message = "Category must be either 'politics', 'sports', or 'technology'")
    private String category;

    @NotEmpty(message = "Image url cannot be empty")
    private String imageUrl;

    private boolean isPublished = false;

    private LocalDate publishDate;
}
