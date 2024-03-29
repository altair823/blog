package org.altair823.blog.service;

import lombok.RequiredArgsConstructor;
import org.altair823.blog.domain.Posts;
import org.altair823.blog.domain.PostsRepository;
import org.altair823.blog.web.dto.PostsListResponseDto;
import org.altair823.blog.web.dto.PostsResponseDto;
import org.altair823.blog.web.dto.PostsSaveRequestDto;
import org.altair823.blog.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No post with ID " + id));
        posts.update(requestDto.title(), requestDto.content());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No post with ID " + id));
        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No post with ID " + id));
        return new PostsResponseDto(posts);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        // Find all posts and sort them by creation date in descending order
        List<Posts> posts = postsRepository.findAll();
        posts.sort((a, b) -> b.getCreatedDateTime().compareTo(a.getCreatedDateTime()));
        return posts.stream().map(PostsListResponseDto::new).toList();
    }
}
