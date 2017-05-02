package springbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springbackend.model.Service;
import springbackend.model.User;

import java.util.List;


public interface ServiceDao extends JpaRepository<Service, Long> {
    void delete(Service service);

    //List<Service> findAllByUser(User user);

    List<Service> findAllByUserId(Long userId);

    List<Service> findAllByCategory(String category);
}
