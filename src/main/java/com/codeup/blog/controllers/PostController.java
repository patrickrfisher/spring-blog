package com.codeup.blog.controllers;

import com.codeup.blog.services.Post;
import com.codeup.blog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

//    List<Post> posts = new ArrayList<>();

    private PostService postService;

    public PostController(PostService postService){
       this.postService = postService;
//        posts.add(new Post("My first post", "It's about my feelings"));
    }

    //    GET	/ads	ads index page
    @GetMapping("/posts")
    public String postsIndex(Model vModel) {
//        posts.add(new Post("A good day", "Yay"));
//        posts.add(new Post("A bad day", "Meh"));
        vModel.addAttribute("posts",postService.findAll());
        return "posts/index";
    }

    //    GET	/ads/{id}	view an individual post
    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable int id, Model vModel) {
        vModel.addAttribute("post", postService.findOne(id));
        return "posts/show";
    }

    //    GET	/ads/create	view the form for creating a post
    @GetMapping("/posts/create")
    public String sendPostForm(Model vModel) {
        vModel.addAttribute("post",new Post());
        return "posts/create";
    }
    //    POST	/ads/create	create a new post
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/posts";
    }

}
