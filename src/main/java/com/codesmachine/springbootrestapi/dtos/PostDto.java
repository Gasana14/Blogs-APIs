package com.codesmachine.springbootrestapi.dtos;

import com.codesmachine.springbootrestapi.domain.Comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private String id;

    // title should not be null or empty
    // title should atleast have two characters

    @NotEmpty
    @NotNull
    @Size(min = 2,message = "Post title should have atleast two characters")
    private String title;

    @NotEmpty
    @NotNull
    @Size(min = 10,message = "Post Description should have atleast ten characters")
    private String description;

    @NotEmpty
    @NotNull
    @Size(max = 20,message = "Post Content should not have more than 20 characters ")
    @Size(min = 5, message = "Post Content should not have less than 5 characters" )
    private String content;
    private Set<CommentDto> comments;

    private String categoryId;
}
