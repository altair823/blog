package org.altair823.blog.web.dto;

import lombok.Getter;
import org.altair823.blog.domain.Posts;

import java.time.format.DateTimeFormatter;

@Getter
public class PostsListResponseDto {
    private final Long id;
    private final String title;
    private final String author;
    private final String createdDateTime;
    private final String modifiedDateTime;

    public PostsListResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.author = posts.getAuthor();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createdDateTime = posts.getCreatedDateTime().format(formatter);
        this.modifiedDateTime = posts.getModifiedDateTime().format(formatter);
    }
}
