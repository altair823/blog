package org.altair823.blog.web;

import lombok.RequiredArgsConstructor;
import org.altair823.blog.service.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class PostsController {
    private final PostsService postsService;

    @GetMapping(path = "/posts/{id}")
    public String postsDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("posts", postsService.findById(id));
        return "content/posts-detail";
    }
}
