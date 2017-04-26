package springbackend.service;

import springbackend.model.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for users services
 */

public interface ServiceForService {
    void save(Service service);

    void saveAndFlush(Service service);

    List<Service> findAllByCategory(String category);
}
