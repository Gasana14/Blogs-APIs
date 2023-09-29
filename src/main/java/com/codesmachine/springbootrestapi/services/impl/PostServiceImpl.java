package com.codesmachine.springbootrestapi.services.impl;

import com.codesmachine.springbootrestapi.domain.Post;
import com.codesmachine.springbootrestapi.dtos.PostDto;
import com.codesmachine.springbootrestapi.exceptions.ResourceNotFoundException;
import com.codesmachine.springbootrestapi.repositories.PostRepository;
import com.codesmachine.springbootrestapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {

        // Convert DTO to Entity
        Post post = convertDtoToPost(postDto);
        Post newPost  = postRepository.save(post);


        // Convert Post to PostDto
        PostDto postDtoResponse = convertPostToDto(newPost);
        return postDtoResponse;
    }

    // Converting Post Entity to Post DTO
    private PostDto convertPostToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    // Convert PostDto to PostEntity
    private Post convertDtoToPost(PostDto postDto){
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }



    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        //Convert Posts to PostDtos
        return posts.stream().map(post -> convertPostToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return convertPostToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto,String id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return convertPostToDto(updatedPost);
    }

    @Override
    public String deletePost(String id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
        return "Post is deleted is succesfully";
    }
}
