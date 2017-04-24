package springbackend.service;

import springbackend.model.User;

/**
 * Service class for {@link springbackend.model.User}
 */

public interface UserService {
    void save(User user, Long roleId);

    void saveAndFlush(User user, Long roleId);

    void delete(User user);

    User findByUsername(String username);

    User findBuId(Long id);

    User findByLogin(String login);
}
