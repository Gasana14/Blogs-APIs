package com.codesmachine.springbootrestapi.services.impl;

import com.codesmachine.springbootrestapi.domain.Comment;
import com.codesmachine.springbootrestapi.domain.Post;
import com.codesmachine.springbootrestapi.dtos.PostDto;
import com.codesmachine.springbootrestapi.dtos.PostPageResponseDto;
import com.codesmachine.springbootrestapi.exceptions.ResourceNotFoundException;
import com.codesmachine.springbootrestapi.repositories.CommentRepository;
import com.codesmachine.springbootrestapi.repositories.PostRepository;
import com.codesmachine.springbootrestapi.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

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
        PostDto postDto = modelMapper.map(post,PostDto.class);
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;
    }

    // Convert PostDto to PostEntity
    private Post convertDtoToPost(PostDto postDto){
        Post post = modelMapper.map(postDto,Post.class);
//        Post post = new Post();
//        post.setId(postDto.getId());
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }



    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        //Convert Posts to PostDtos
        return posts.stream().map(post -> convertPostToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostPageResponseDto getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        Page<Post> postsPage = postRepository.findAll(pageable);

        // get page content
        List<Post> posts = postsPage.getContent();

        //Convert Posts to PostDtos
        List<PostDto> postDtos = posts.stream().map(post -> convertPostToDto(post)).collect(Collectors.toList());

       // Build Post Response
        PostPageResponseDto postResponse = new PostPageResponseDto();
        postResponse.setContent(postDtos);
        postResponse.setPageSize(postsPage.getSize());
        postResponse.setPageNo(postsPage.getNumber());
        postResponse.setTotalElements(postsPage.getTotalElements());
        postResponse.setTotalPages(postsPage.getTotalPages());
        postResponse.setLast(postsPage.isLast());

        return postResponse;

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
