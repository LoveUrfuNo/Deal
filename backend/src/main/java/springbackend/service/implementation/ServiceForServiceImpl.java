package springbackend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import springbackend.dao.ServiceDao;
import springbackend.model.Service;
import springbackend.service.ServiceForService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link ServiceForService} interface.
 */

@org.springframework.stereotype.Service
public class ServiceForServiceImpl implements ServiceForService {
    @Autowired
    private ServiceDao serviceDao;

    @Override
    public void save(Service service) {
        serviceDao.save(service);
    }

    @Override
    public void saveAndFlush(Service service) {
        serviceDao.saveAndFlush(service);
    }

    @Override
    public List<Service> findAllByCategory(String category) {
        return serviceDao.findAll().stream()
                .filter(temp -> temp.getCategory().equals(category)).collect(Collectors.toList());
    }
}
