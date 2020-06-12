package repositories;

import model.SmartPhone;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SmartPhoneRepository extends PagingAndSortingRepository<SmartPhone, Long> {
}
