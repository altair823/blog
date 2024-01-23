package org.altair823.blog.web.dto;

public record PostsUpdateRequestDto(String title, String content) {

    @Override
    public String toString() {
        return "PostsUpdateRequestDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
