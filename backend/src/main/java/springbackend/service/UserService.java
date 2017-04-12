package springbackend.service;

import springbackend.model.User;

/**
 * Service class for {@link springbackend.model.User}
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
