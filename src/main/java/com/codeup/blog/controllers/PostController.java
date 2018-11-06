package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.services.PostService;
import com.codeup.blog.repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

//    List<Post> posts = new ArrayList<>();

    private PostService postService;
    private UserRepo userRepo;

    public PostController(PostService service, UserRepo userRepo){
        this.postService = service;
        this.userRepo = userRepo;
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
    public String individualPost(@PathVariable Long id, Model vModel) {
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
        User loggedInUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(userRepo.findOne(loggedInUser.getId()));
        postService.edit(post);
        return "redirect:/posts/"+ post.getId();
    }
    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable Long id, Model vModel){
        vModel.addAttribute("post",postService.findOne(id));
        return "posts/edit";
    }


    @PostMapping("/posts/{id}/edit")
    public String sendEdit(@ModelAttribute Post post, @PathVariable Long id){
        User loggedInUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (postService.findOne(id).getUser().getId() == loggedInUser.getId()) {
            post.setUser(userRepo.findByUsername(loggedInUser.getUsername()));
            postService.edit(post);
            return "redirect:/posts";
        } else {
            return "redirect:/";
        }}

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@ModelAttribute Post post, @PathVariable Long id){
        User loggedInUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (postService.findOne(id).getUser().getId() == loggedInUser.getId()) {
        postService.delete(post);
        return "redirect:/posts";
        } else {
            return "redirect:/";
        }
    }
}
