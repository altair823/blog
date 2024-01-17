package org.altair823.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(path = "/home")
    public String home() {
        return "content/home";
    }

    @GetMapping(path = "/")
    public String index() {
        return "content/index";
    }
}
