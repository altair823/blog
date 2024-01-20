package org.altair823.blog.config.auth.dto;

import lombok.Getter;
import org.altair823.blog.domain.BlogUser;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(BlogUser blogUser) {
        this.name = blogUser.getName();
        this.email = blogUser.getEmail();
        this.picture = blogUser.getPicture();
    }
}
