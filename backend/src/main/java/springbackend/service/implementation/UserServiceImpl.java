package springbackend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springbackend.dao.RoleDao;
import springbackend.dao.UserDao;
import springbackend.model.Role;
import springbackend.model.User;
import springbackend.service.UserService;

import java.util.*;

/**
 * Implementation of {@link UserService} interface.
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
//    @CacheEvict(value="user", allEntries=true)
    public void save(User user, Long roleId) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(roleId));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
//    @CacheEvict(value="user", allEntries=true)
    public void saveAndFlush(User user, Long roleId) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(roleId));
        user.setRoles(roles);
        userDao.saveAndFlush(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
//    @Cacheable(value="user")
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
//    @Cacheable(value="user")
    public User findByLogin(String login) {
        return userDao.findAll().stream()
                .filter(temp -> login.equals(temp.getLogin())).findAny().orElse(null);
    }

    @Override
//    @Cacheable(value="user")
    public User findBuId(Long id) {
        return userDao.findById(id);
    }
}
