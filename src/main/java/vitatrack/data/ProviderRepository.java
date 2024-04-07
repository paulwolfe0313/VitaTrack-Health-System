package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vitatrack.Provider;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Provider findProviderById(Long id);
    Provider findProviderByUserName(String userName);
    Provider findProviderByUserNameAndPassWord(String userName, String passWord);


}
