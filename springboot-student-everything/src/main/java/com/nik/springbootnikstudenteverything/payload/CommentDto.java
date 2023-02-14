package com.nik.springbootnikstudenteverything.payload;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CommentDto {
    private long id;
    private String name;
    private String comment;
    private String email;
}
