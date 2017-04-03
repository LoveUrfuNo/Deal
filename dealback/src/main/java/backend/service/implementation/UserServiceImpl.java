package backend.service.implementation;

import backend.dao.UserRepositoryImpl;
import backend.model.User;
import backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andrey on 29.03.17.
 */

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Override
    public User addUser(User user) {
        return userRepositoryImpl.saveAndFlush(user);
    }

    @Override
    public void delete(long id) {
        userRepositoryImpl.delete(id);
    }

    @Override
    public User getByName(String name) {
        return userRepositoryImpl.findByName(name);
    }

    @Override
    public User editUser(User user) {
        return userRepositoryImpl.saveAndFlush(user);
    }

    @Override
    public List<User> getAll() {
        return userRepositoryImpl.findAll();
    }
}
