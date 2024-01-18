package org.altair823.blog.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postRepository;

    @AfterEach
    public void cleanUp() {
        postRepository.deleteAll();
    }

//    @Test
    public void savePost() {
//        String title = "테스트 게시글";
//        String content = "테스트 본문";
//
//        postRepository.save(Posts.builder()
//                .title(title)
//                .content(content)
//                .author("altair823")
//                .build());
//
//        // when
//        List<Posts> postsList = postRepository.findAll();
//
//        // then
//        Posts posts = postsList.get(0);
//        assert posts.getTitle().equals(title);
//        assert posts.getContent().equals(content);
    }
}
