package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vitatrack.User;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<User, Long> {
}
