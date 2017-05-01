package springbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springbackend.model.PersistentUser;

public interface PersistentUserDao extends JpaRepository<PersistentUser, Long> {
    public PersistentUser getByUsername(String username);
}
