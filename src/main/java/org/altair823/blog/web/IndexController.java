package org.altair823.blog.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.altair823.blog.config.auth.LoginUser;
import org.altair823.blog.config.auth.dto.SessionUser;
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
    private final HttpSession httpSession;

    @GetMapping(path = "/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "content/index";
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
}
