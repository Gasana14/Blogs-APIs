package com.codesmachine.springbootrestapi.services;

import com.codesmachine.springbootrestapi.domain.Comment;
import com.codesmachine.springbootrestapi.dtos.CommentDto;

import java.util.List;
import java.util.Set;

public interface CommentService {
        CommentDto createComment(String postId, CommentDto commentDto);
        Set<CommentDto> getAllCommentsByPostId(String postId);

        CommentDto getCommentByCommentIdAndByPostId(String commentId,String postId);

        CommentDto updateCommentByCommentIdAndByPostId(String commentId,String postId,CommentDto commentDto);

        String deleteCommentFromPost(String commentId,String postId);
}
