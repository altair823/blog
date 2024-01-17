package org.altair823.blog.web;

import org.altair823.blog.domain.Posts;
import org.altair823.blog.domain.PostsRepository;
import org.altair823.blog.web.dto.PostsSaveRequestDto;
import org.altair823.blog.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    public void savePost() {
        String title = "title_test";
        String content = "content_test";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author_test")
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assert responseEntity.getStatusCode().is2xxSuccessful();
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody() > 0L;

        List<Posts> all = postsRepository.findAll();
        assert all.get(0).getTitle().equals(title);
        assert all.get(0).getContent().equals(content);
    }

    @Test
    public void updateTest() {
        Posts savedPost = postsRepository.save(Posts.builder()
                .title("title_test")
                .content("content_test")
                .author("author_test")
                .build());
        Long updateId = savedPost.getId();
        String expectedTitle = "title_test2";
        String expectedContent = "content_test2";
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestDtoHttpEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestDtoHttpEntity, Long.class);

        assert responseEntity.getStatusCode().is2xxSuccessful();
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody() > 0L;

        List<Posts> all = postsRepository.findAll();
        assert all.get(0).getTitle().equals(expectedTitle);
        assert all.get(0).getContent().equals(expectedContent);
    }
}
