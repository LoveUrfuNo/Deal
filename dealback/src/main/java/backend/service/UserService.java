package backend.service;

import backend.model.User;

/**
 * Service class for {@link backend.model.User}
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
