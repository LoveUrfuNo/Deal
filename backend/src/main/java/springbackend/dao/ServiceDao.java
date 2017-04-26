package springbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springbackend.model.Service;

public interface ServiceDao extends JpaRepository<Service, Long> {

}
