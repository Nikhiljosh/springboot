package com.nik.springbootnikstudenteverything.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nik.springbootnikstudenteverything.entity.Post;
import com.nik.springbootnikstudenteverything.exception.ResourceNotFoundException;
import com.nik.springbootnikstudenteverything.payload.PostDto;
import com.nik.springbootnikstudenteverything.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);

        Post savePost =  postRepository.save(post);
        PostDto responsePostDto = mapToDto(savePost);

        return responsePostDto;
    }

    @Override
    public List<PostDto> getAllPosts(int pageSize) {

        Pageable pageable1 = Pageable.ofSize(pageSize);
        Page<Post> postList = postRepository.findAll(pageable1);

        return postList.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

    }

    @Override
    public PostDto getPostById(Long id) {

        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {

        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        postRepository.save(post);

        return mapToDto(post);
    }

    @Override
    public void deletePostById(long id) {

        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);

    }

    private PostDto mapToDto(Post post){

        PostDto postDto = modelMapper.map(post,PostDto.class);
        /*PostDto postDto = new PostDto();

        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());*/

        return postDto;

    }

    private Post mapToEntity(PostDto postDto){

        Post post = modelMapper.map(postDto, Post.class);

       /* Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());*/

        return post;

    }
}
