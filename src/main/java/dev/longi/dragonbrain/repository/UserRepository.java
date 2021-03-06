package dev.longi.dragonbrain.repository;

import dev.longi.dragonbrain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lngtr
 * @since 2017-12-25
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
