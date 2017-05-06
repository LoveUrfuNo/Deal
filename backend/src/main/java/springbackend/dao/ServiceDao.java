package springbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springbackend.model.Service;
import springbackend.model.User;

import java.util.List;

/**
 * Dao for class {@link springbackend.model.Service}.
 */

public interface ServiceDao extends JpaRepository<Service, Long> {
    void delete(Service service);

    Service findById(Long id);

    List<Service> findAllByUserId(Long userId);

    List<Service> findAllByCategory(String category);
}
