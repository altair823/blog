package org.altair823.blog.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.altair823.blog.config.auth.LoginUser;
import org.altair823.blog.config.auth.dto.SessionUser;
import org.altair823.blog.service.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


}
