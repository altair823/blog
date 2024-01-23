package org.altair823.blog.web;

import lombok.RequiredArgsConstructor;
import org.altair823.blog.service.PostsService;
import org.altair823.blog.web.dto.PostsSaveRequestDto;
import org.altair823.blog.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostsController {
    private final PostsService postsService;

    @GetMapping(path = "/posts/{id}")
    public String postsDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("posts", postsService.findById(id));
        return "content/posts-detail";
    }

    @GetMapping(path = "/posts-save")
    public String postsSave(Model model, PostsSaveRequestDto requestDto) {
        model.addAttribute("requestDto", requestDto);
        return "content/posts-save";
    }
    @PostMapping(path = "/posts-save")
    public String save(@ModelAttribute("requestDto") PostsSaveRequestDto requestDto) {
        postsService.save(requestDto);
        return "redirect:/";
    }

    @GetMapping(path = "/posts-update/{id}")
    public String postsUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("requestDto", postsService.findById(id));
        return "content/posts-update";
    }

    @PutMapping(path = "/posts-update/{id}")
    public String update(@PathVariable("id") Long id, PostsUpdateRequestDto requestDto) {
        System.out.println("requestDto: " + requestDto.toString());
        postsService.update(id, requestDto);
        return "redirect:/posts/{id}";
    }
}
