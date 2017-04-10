package springbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springbackend.model.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
}