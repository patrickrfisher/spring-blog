package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String post(){
        return "post index page or these are all the post";
    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postId(@PathVariable int id){
        return "this is the " + id;
    }
    @GetMapping("/posts/create")
    @ResponseBody
    public String postCreate(){
        return "Create a post!!!!";
    }
    @PostMapping("/post/create")
    @ResponseBody
    public String createPost()
    {
        return "you crated a  post";
    }

}

