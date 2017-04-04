package backend.service;

import backend.model.User;

import java.util.List;

/**
 * Created by andrey on 28.03.17.
 */

public interface UserService {

    User addUser(User user);
    void delete(long id);
    User getByName(String name);
    User editUser(User user);
    List<User> getAll();
}
