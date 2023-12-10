package com.codesmachine.springbootrestapi.controllers;

import com.codesmachine.springbootrestapi.dtos.PostDto;
import com.codesmachine.springbootrestapi.dtos.PostDtoV2;
import com.codesmachine.springbootrestapi.dtos.PostPageResponseDto;
import com.codesmachine.springbootrestapi.services.impl.PostServiceImpl;
import com.codesmachine.springbootrestapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {

    @Autowired
    private PostServiceImpl postService;


    // Create Blog Post rest api
    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to create Post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // Update Blog Post by id
    @Operation(
            summary = "Update Post REST API",
            description = "Update Post REST API is used to update Post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/update")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name = "id") String postId){
        return ResponseEntity.ok(postService.updatePost(postDto,postId));
    }

    // Get all Blog Posts rest api
    @Operation(
            summary = "Get All Posts REST API",
            description = "Get All Posts REST API is used to Find all Posts from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }


    // Get all Blog Posts rest api with pagination & sorting features (Tables & Pages)
    @Operation(
            summary = "Get All Posts into Page / Table REST API",
            description = "Get All Posts into Page / Table REST API is used to Find all Posts from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
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
// START Version REST APIs by changing URL -------------------------------------------------------------------------
//    @Operation(
//            summary = "Get Post By Id REST API",
//            description = "Get Post By Id REST API is used to find Post from database by id"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Http Status 200 Success"
//    )
//    @GetMapping("/api/v1/posts/{id}")
//    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name = "id") String postId){
//        return ResponseEntity.ok(postService.getPostById(postId));
//    }


//    @Operation(
//            summary = "Get Post By Id REST API",
//            description = "Get Post By Id REST API is used to find Post from database by id"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Http Status 200 Success"
//    )
//    @GetMapping("/api/v2/posts/{id}")
//    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name = "id") String postId){
//        PostDto postDto =postService.getPostById(postId);
//        PostDtoV2 postDtoV2 = new PostDtoV2();
//
//        postDtoV2.setId(postDto.getId());
//        postDtoV2.setTitle(postDto.getTitle());
//        postDtoV2.setContent(postDto.getContent());
//        postDtoV2.setDescription(postDto.getDescription());
//        postDtoV2.setCategoryId(postDto.getCategoryId());
//        postDtoV2.setComments(postDto.getComments());
//        List<String> tags = new ArrayList<>();
//        tags.add("Java");
//        tags.add("Python");
//        postDtoV2.setTags(tags);
//        return ResponseEntity.ok(postDtoV2);
//    }
//
//    // END Version REST APIs by changing URL ---------------------------------------------------------------------------



    // START Version REST APIs by Query Param -------------------------------------------------------------------------
    @Operation(
            summary = "Get Post By Id REST API",
            description = "Get Post By Id REST API is used to find Post from database by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping(value = "/{id}",params = "version=1")
    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name = "id") String postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }
    @Operation(
            summary = "Get Post By Id REST API",
            description = "Get Post By Id REST API is used to find Post from database by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping(value = "/{id}",params = "version=2")
    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name = "id") String postId){
        PostDto postDto =postService.getPostById(postId);
        PostDtoV2 postDtoV2 = new PostDtoV2();

        postDtoV2.setId(postDto.getId());
        postDtoV2.setTitle(postDto.getTitle());
        postDtoV2.setContent(postDto.getContent());
        postDtoV2.setDescription(postDto.getDescription());
        postDtoV2.setCategoryId(postDto.getCategoryId());
        postDtoV2.setComments(postDto.getComments());
        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("Python");
        postDtoV2.setTags(tags);
        return ResponseEntity.ok(postDtoV2);
    }

    // END Version REST APIs by Query Param ---------------------------------------------------------------------------

    // Delete Blog Post rest api
    @Operation(
            summary = "Delete Post By Id REST API",
            description = "Delete Post By Id REST API is used to delete Post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(postService.deletePost(id));
    }

    // Build Get REST API to fetch all posts that belongs to a certain category
    @Operation(
            summary = "Get All Posts By Category REST API",
            description = "REST API to fetch all posts that belongs to a certain category"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(name = "id") String id){
        List<PostDto> postDtos = postService.getPostsByCategory(id);
        return ResponseEntity.ok(postDtos);
    }


}
