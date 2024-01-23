package org.altair823.blog.web.dto;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import lombok.Getter;
import org.altair823.blog.domain.Posts;

import java.time.format.DateTimeFormatter;

@Getter
public class PostsResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final String createdDateTime;
    private final String modifiedDateTime;

    public PostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = convertMarkdownToHtml(posts.getContent());
        this.author = posts.getAuthor();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createdDateTime = posts.getCreatedDateTime().format(formatter);
        this.modifiedDateTime = posts.getModifiedDateTime().format(formatter);
    }

    public String convertMarkdownToHtml(String markdownContent) {
        Parser parser = Parser.builder().build();
        Document document = parser.parse(markdownContent);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    @Override
    public String toString() {
        return "PostsResponseDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", createdDateTime='" + createdDateTime + '\'' +
                ", modifiedDateTime='" + modifiedDateTime + '\'' +
                '}';
    }
}
