package springbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springbackend.model.UserFile;

/**
 * Dao for class {@link springbackend.model.UserFile}.
 */

public interface UserFileDao extends JpaRepository<UserFile, Long> {

}
