package com.codemon.requestapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Post {
    private long id;
    private String title;
    private String body;
    private long userId;
}
