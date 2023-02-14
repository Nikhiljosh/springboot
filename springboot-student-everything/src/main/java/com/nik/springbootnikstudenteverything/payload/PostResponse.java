package com.nik.springbootnikstudenteverything.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostResponse {

    private List<PostDto> content;
    private int pageSize;
    private long totalElements;
    private boolean last;
}
