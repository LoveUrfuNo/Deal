package backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import backend.model.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
}
