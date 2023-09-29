package com.codesmachine.springbootrestapi.dtos;

import lombok.Data;

@Data
public class PostDto {
    private String id;
    private String title;
    private String description;
    private String content;
}
