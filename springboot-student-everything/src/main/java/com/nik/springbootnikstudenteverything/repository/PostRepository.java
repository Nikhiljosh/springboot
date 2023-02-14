package com.nik.springbootnikstudenteverything.repository;

import com.nik.springbootnikstudenteverything.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
