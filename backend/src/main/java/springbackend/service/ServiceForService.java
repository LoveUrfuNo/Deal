package springbackend.service;

import springbackend.model.Service;
import springbackend.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Service for users services
 */

public interface ServiceForService {
    void delete(Service service);

    void save(Service service);

    void saveAndFlush(Service service);

    Service findByUserId(Long userId);

    Service findById(Long id);

    Set<Service> findAll();

    Set<Service> findAllByCategory(String category);

    Set<Service> findAllByUserId(Long userId);
}
