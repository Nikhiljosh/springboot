package com.nik.springbootnikstudenteverything.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nik.springbootnikstudenteverything.entity.Comment;
import com.nik.springbootnikstudenteverything.entity.Post;
import com.nik.springbootnikstudenteverything.exception.BlogApiException;
import com.nik.springbootnikstudenteverything.exception.ResourceNotFoundException;
import com.nik.springbootnikstudenteverything.payload.CommentDto;
import com.nik.springbootnikstudenteverything.repository.CommentRepository;
import com.nik.springbootnikstudenteverything.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToComment(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);

        return mapToDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long id) {

        List<Comment> commentList = commentRepository.findByPostId(id);

        return commentList.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentByIdFromPost(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Post and Comment does not match");
        }
        return mapToDto(comment);
    }

    @Override
    public List<CommentDto> getAllComments() {

        return commentRepository.findAll().stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Post and Comment does not match");
        }

        comment.setComment(commentDto.getComment());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());

        return mapToDto(commentRepository.save(comment));
    }

    @Override
    public void deleteById(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Post and Comment does not match");
        } else {
            commentRepository.delete(comment);
        }
    }

    public Comment mapToComment(CommentDto commentDto) {

        Comment comment = modelMapper.map(commentDto, Comment.class);
        /*Comment comment = new Comment();

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setComment(commentDto.getComment());*/

        return comment;

    }

    public CommentDto mapToDto(Comment comment) {

        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);

        /* CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setComment(comment.getComment());*/

        return commentDto;
    }
}
