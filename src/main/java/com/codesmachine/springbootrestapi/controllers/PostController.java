package com.codesmachine.springbootrestapi.controllers;

import com.codesmachine.springbootrestapi.dtos.PostDto;
import com.codesmachine.springbootrestapi.services.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;


    // Create Blog Post rest api
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // Get all Blog Posts rest api
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // Get Blog Post by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") String postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    // Update Blog Post by id
    @PutMapping("/{id}/update")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name = "id") String postId){
        return ResponseEntity.ok(postService.updatePost(postDto,postId));
    }


    // Delete Blog Post rest api
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(postService.deletePost(id));
    }


}
