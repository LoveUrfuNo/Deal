package springbackend.service;

import springbackend.model.UserFile;

import java.util.Set;

/**
 * Service class for {@link springbackend.model.UserFile}
 */

public interface UserFileService {
    void save(UserFile file);

    void saveAndFlush(UserFile file);

    void delete(UserFile file);

    Set<UserFile> findAll();

    Set<UserFile> findAllByUserId(Long id);

    Set<UserFile> findAllByServiceName(String name);
}
