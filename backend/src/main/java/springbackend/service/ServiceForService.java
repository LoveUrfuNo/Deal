package springbackend.service;

import springbackend.model.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for users services
 */

public interface ServiceForService {
    void delete(Service service);

    void save(Service service);

    void saveAndFlush(Service service);

    Service findByUserId(Long userId);

    List<Service> findAllByCategory(String category);

    List<Service> findAllByUserId(Long userId);
}
