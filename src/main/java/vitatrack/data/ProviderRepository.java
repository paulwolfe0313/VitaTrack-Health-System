package vitatrack.data;

import org.springframework.stereotype.Repository;
import vitatrack.Provider;

@Repository
public interface ProviderRepository extends UserRepository<Provider>{

    Provider findProviderByUserName(String userName);
    Provider findProviderByUserNameAndPassWord(String userName, String passWord);

}
