package backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import backend.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
