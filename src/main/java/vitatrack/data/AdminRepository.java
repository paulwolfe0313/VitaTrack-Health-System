package vitatrack.data;

import org.springframework.stereotype.Repository;
import vitatrack.Admin;

@Repository
public interface AdminRepository extends UserRepository<Admin> {
}
