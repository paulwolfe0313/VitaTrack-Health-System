package vitatrack.data;

import org.springframework.stereotype.Repository;
import vitatrack.Admin;

@Repository
public interface AdminRepository extends UserRepository<Admin> {

    Admin findAdminByUserName(String userName);
    Admin findAdminByUserNameAndPassWord(String userName, String passWord);
}
