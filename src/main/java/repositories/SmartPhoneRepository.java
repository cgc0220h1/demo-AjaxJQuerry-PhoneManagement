package repositories;

import model.SmartPhone;
import org.springframework.data.repository.CrudRepository;

public interface SmartPhoneRepository extends CrudRepository<SmartPhone, Long> {
}
