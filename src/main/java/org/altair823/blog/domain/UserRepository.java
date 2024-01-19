package org.altair823.blog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<BlogUser, Long> {
    Optional<BlogUser> findByEmail(String email);
}
