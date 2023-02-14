package com.nik.springbootnikstudenteverything.service;

import com.nik.springbootnikstudenteverything.entity.Post;
import com.nik.springbootnikstudenteverything.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts(int pageSize);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
