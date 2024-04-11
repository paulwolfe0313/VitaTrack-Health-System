package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vitatrack.Users;

@Repository
public interface UserRepository<T extends Users> extends JpaRepository<Users, Long> {
}
