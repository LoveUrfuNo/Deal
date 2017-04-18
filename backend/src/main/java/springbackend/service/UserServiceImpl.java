package springbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springbackend.dao.RoleDao;
import springbackend.dao.UserDao;
import springbackend.model.Role;
import springbackend.model.User;

import java.util.*;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public void saveAndFlush(User user) {
        userDao.saveAndFlush(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByLogin(String login) {
        ArrayList<User> list = (ArrayList<User>) userDao.findAll();
        User result = list.stream().filter(search -> login.equals(search.getLogin())).findAny().orElse(null);
        return result;
    }

    @Override
    public User findBuId(Long id) {
        return userDao.findById(id);
    }
}
