package org.altair823.blog.config.auth.dto;

import lombok.Getter;
import org.altair823.blog.domain.BlogUser;

@Getter
public class SessionUser {
    private String name;
    private String email;
    private String picture;

    public SessionUser(BlogUser blogUser) {
        this.name = blogUser.getName();
        this.email = blogUser.getEmail();
        this.picture = blogUser.getPicture();
    }
}
