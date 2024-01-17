package org.altair823.blog.web;

import lombok.RequiredArgsConstructor;
import org.altair823.blog.service.PostsService;
import org.altair823.blog.web.dto.PostsResponseDto;
import org.altair823.blog.web.dto.PostsSaveRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping(path = "/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping(path = "/api/v1/posts/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody PostsSaveRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping(path = "/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable("id") Long id) {
        return postsService.findById(id);
    }
}
