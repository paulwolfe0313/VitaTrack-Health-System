package vitatrack;

import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Admin extends User{

    @OneToMany(mappedBy = "admin")
    private Bill bill;


}
