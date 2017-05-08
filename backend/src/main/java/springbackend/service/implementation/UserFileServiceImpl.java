package springbackend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbackend.dao.UserFileDao;
import springbackend.model.UserFile;
import springbackend.service.UserFileService;

/**
 * Implementation of {@link springbackend.service.UserFileService} interface.
 */

@Service
public class UserFileServiceImpl implements UserFileService {
    @Autowired
    private UserFileDao userFileDao;

    @Override
    public void save(UserFile file) {
        this.userFileDao.save(file);
    }
}
