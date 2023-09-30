package com.codesmachine.springbootrestapi.services;

import com.codesmachine.springbootrestapi.dtos.PostDto;
import com.codesmachine.springbootrestapi.dtos.PostPageResponseDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostPageResponseDto getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto getPostById(String id);

    PostDto updatePost(PostDto postDto,String id);

    String deletePost(String id);

}
