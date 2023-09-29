package com.codesmachine.springbootrestapi.services;

import com.codesmachine.springbootrestapi.dtos.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();

    PostDto getPostById(String id);

    PostDto updatePost(PostDto postDto,String id);

    String deletePost(String id);

}
