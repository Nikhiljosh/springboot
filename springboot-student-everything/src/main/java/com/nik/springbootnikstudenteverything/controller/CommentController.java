package com.nik.springbootnikstudenteverything.controller;

import com.nik.springbootnikstudenteverything.entity.Comment;
import com.nik.springbootnikstudenteverything.payload.CommentDto;
import com.nik.springbootnikstudenteverything.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CommentDto commentDto) {

        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public List<CommentDto> getAllCommentsFromPostId(@PathVariable long postId) {

        return commentService.getCommentsByPostId(postId);
    }


    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getAllCommentsFromPostId(@PathVariable long postId, @PathVariable long commentId) {

        return new ResponseEntity<>(commentService.getCommentByIdFromPost(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> update(@PathVariable long postId, @PathVariable long commentId, @RequestBody CommentDto commentDto) {

        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> getDeleteFromPostId(@PathVariable long postId, @PathVariable long commentId) {

        commentService.deleteById(postId, commentId);
        return new ResponseEntity<>("Deleted the comment with id: " + commentId + " post id: " + postId, HttpStatus.OK);
    }


    @GetMapping("/comments")
    public List<CommentDto> getAllComments(){
        return commentService.getAllComments();
    }
}
