package com.codesmachine.springbootrestapi.controllers;

import com.codesmachine.springbootrestapi.dtos.PostDto;
import com.codesmachine.springbootrestapi.dtos.PostPageResponseDto;
import com.codesmachine.springbootrestapi.services.impl.PostServiceImpl;
import com.codesmachine.springbootrestapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;


    // Create Blog Post rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // Update Blog Post by id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/update")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name = "id") String postId){
        return ResponseEntity.ok(postService.updatePost(postDto,postId));
    }

    // Get all Blog Posts rest api
    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }


    // Get all Blog Posts rest api with pagination & sorting features (Tables & Pages)
    @GetMapping("/page")
    public PostPageResponseDto getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    // Get Blog Post by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") String postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }




    // Delete Blog Post rest api
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(postService.deletePost(id));
    }

    // Build Get REST API to fetch all posts that belongs to a certain category
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(name = "id") String id){
        List<PostDto> postDtos = postService.getPostsByCategory(id);
        return ResponseEntity.ok(postDtos);
    }


}
