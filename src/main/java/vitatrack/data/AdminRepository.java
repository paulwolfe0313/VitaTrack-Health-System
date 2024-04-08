package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vitatrack.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findAdminByUserName(String userName);
    Admin findAdminByUserNameAndPassWord(String userName, String passWord);
}
