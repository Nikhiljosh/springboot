package com.nik.springbootnikstudenteverything.service;

import com.nik.springbootnikstudenteverything.entity.Comment;
import com.nik.springbootnikstudenteverything.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long id);

    CommentDto getCommentByIdFromPost(long postId, long commentId);
    List<CommentDto> getAllComments();

    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);

    void deleteById(long postId, long commentId);

}
