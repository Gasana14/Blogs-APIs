package com.codesmachine.springbootrestapi.services.impl;


import com.codesmachine.springbootrestapi.domain.Comment;
import com.codesmachine.springbootrestapi.domain.Post;
import com.codesmachine.springbootrestapi.dtos.CommentDto;
import com.codesmachine.springbootrestapi.dtos.PostDto;
import com.codesmachine.springbootrestapi.exceptions.BlogAPIsException;
import com.codesmachine.springbootrestapi.exceptions.ResourceNotFoundException;
import com.codesmachine.springbootrestapi.repositories.CommentRepository;
import com.codesmachine.springbootrestapi.repositories.PostRepository;
import com.codesmachine.springbootrestapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;



    @Override
    public CommentDto createComment(String postId, CommentDto commentDto) {
        Comment comment = convertDtoToComment(commentDto);

        // retrieve post by post id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));

        // set post to comment entity
        comment.setPost(post);

        // save comment entity
       Comment savedComment = commentRepository.save(comment);


        return convertCommentToDto(savedComment);
    }

    @Override
    public Set<CommentDto> getAllCommentsByPostId(String postId) {
        // fetch comments by post id
        Set<Comment> comments = commentRepository.findByPostId(postId);
        // convert set of comment entities to set of comment dtos
        return comments.stream().map(comment -> convertCommentToDto(comment)).collect(Collectors.toSet());
    }

    @Override
    public CommentDto getCommentByCommentIdAndByPostId(String commentId, String postId) {

        // get comment by id and by post id
        Comment comment = commentRepository.findByIdAndPostId(commentId,postId);
        if(comment==null){
           throw new BlogAPIsException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        return convertCommentToDto(comment);
    }

    @Override
    public CommentDto updateCommentByCommentIdAndByPostId(String commentId, String postId,CommentDto commentDto) {
        // get comment by id and by post id
        Comment comment = commentRepository.findByIdAndPostId(commentId,postId);

        // update comment
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        // update comment
       Comment updatedComment = commentRepository.save(comment);

        // return Converted Dto
        return convertCommentToDto(updatedComment);

    }

    @Override
    public String deleteCommentFromPost(String commentId, String postId) {
        // get comment by id and by post id
        Comment comment = commentRepository.findByIdAndPostId(commentId,postId);
        commentRepository.delete(comment);
        return "Comment has been deleted from "+comment.getPost().getTitle()+" post successfully";
    }


    // Converting Comment Entity to Comment DTO
    private CommentDto convertCommentToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    // Convert CommentDto to CommentEntity
    private Comment convertDtoToComment(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        return comment;
    }
}
