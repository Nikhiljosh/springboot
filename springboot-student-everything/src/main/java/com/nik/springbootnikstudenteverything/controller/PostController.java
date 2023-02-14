package com.nik.springbootnikstudenteverything.controller;

import com.nik.springbootnikstudenteverything.entity.Post;
import com.nik.springbootnikstudenteverything.payload.PostDto;
import com.nik.springbootnikstudenteverything.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){

        return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    @GetMapping
    public List<PostDto> getAllPosts(@RequestParam(value = "pageSize", defaultValue = "15", required = false)int pageSize){

        return postService.getAllPosts(pageSize);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){

        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.ACCEPTED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable long id){

        return new ResponseEntity<>(postService.updatePost(postDto,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id){

        postService.deletePostById(id);
        return new ResponseEntity<>("Post with id: "+id+" deleted ", HttpStatus.OK);
    }
}
