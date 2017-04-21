package springbackend.dao;

        import org.springframework.data.jpa.repository.JpaRepository;
        import springbackend.model.PersistentUser;

/**
 * Created by kosty on 21.04.2017.
 */
public interface PersistentUserDao extends JpaRepository<PersistentUser, Long> {
    public PersistentUser getByUsername(String username);

//    public PersistentUser getById(Long id);
}
