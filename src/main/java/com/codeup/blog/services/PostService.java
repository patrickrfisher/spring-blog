package com.codeup.blog.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private List<Post> posts;

    public PostService(){
        posts=new ArrayList<>();
        posts.add(new Post("IT","Pennywise"));
        posts.add(new Post("IT2","Pennywise2"));
    }


    public List<Post> findAll(){
        return posts;
    }

    public Post findOne(int id){
        return posts.get(id-1);
    }
    public Post save(Post post){
        post.setId((long)posts.size()+1);
        posts.add(post);
        return post;
    }

}
