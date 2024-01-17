package org.altair823.blog.web;

import lombok.RequiredArgsConstructor;
import org.altair823.blog.domain.Posts;
import org.altair823.blog.service.PostsService;
import org.altair823.blog.web.dto.PostsSaveRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping(path = "/home")
    public String home() {
        return "content/home";
    }

    @GetMapping(path = "/")
    public String index() {
        return "content/index";
    }

    @GetMapping(path = "/posts/save")
    public String postsSave(Model model, PostsSaveRequestDto requestDto) {
        model.addAttribute("requestDto", requestDto);
        return "content/posts-save";
    }
    @PostMapping(path = "/posts/save")
    public String save(@ModelAttribute("requestDto") PostsSaveRequestDto requestDto) {
        postsService.save(requestDto);
        return "redirect:/";
    }
}
