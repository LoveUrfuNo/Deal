package springbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springbackend.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}