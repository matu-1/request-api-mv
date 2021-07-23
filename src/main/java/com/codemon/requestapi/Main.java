package com.codemon.requestapi;

import com.codemon.requestapi.models.Post;
import com.codemon.requestapi.utils.Fetch;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        try {
            Post body = new Post(1, "title", "soy un body", 2);
            String result = Fetch.post("https://jsonplaceholder.typicode.com/posts", body);
            Post post = new ObjectMapper().readValue(result, Post.class);
            System.out.println(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
