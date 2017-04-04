package backend.dao;

import backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by andrey on 28.03.17.
 */

@Repository
public interface UserRepositoryImpl extends JpaRepository<User, Long> {
    @Query(value = "select u from User u where u.name = :name")
    User findByName(@Param("name") String name);
}
