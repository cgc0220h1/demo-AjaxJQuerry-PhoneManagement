package service.phone;

import model.SmartPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repositories.SmartPhoneRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class SmartPhoneServiceImp implements SmartPhoneService {
    private final SmartPhoneRepository smartPhoneRepository;

    @Autowired
    public SmartPhoneServiceImp(SmartPhoneRepository smartPhoneRepository) {
        this.smartPhoneRepository = smartPhoneRepository;
    }

    @Override
    public List<SmartPhone> findAll() {
        List<SmartPhone> smartPhones = new LinkedList<>();
        Iterable<SmartPhone> iterable = smartPhoneRepository.findAll();
        for (SmartPhone smartPhone : iterable) {
            smartPhones.add(smartPhone);
        }
        return smartPhones;
    }

    @Override
    public List<SmartPhone> findAll(Sort sort) {
        List<SmartPhone> smartPhones = new LinkedList<>();
        Iterable<SmartPhone> iterable = smartPhoneRepository.findAll(sort);
        for (SmartPhone smartPhone : iterable) {
            smartPhones.add(smartPhone);
        }
        return smartPhones;
    }

    @Override
    public Page<SmartPhone> findAll(Pageable pageable) {
        return smartPhoneRepository.findAll(pageable);
    }

    @Override
    public SmartPhone findOne(Long id) {
        return smartPhoneRepository.findById(id).orElse(null);
    }

    @Override
    public SmartPhone save(SmartPhone smartPhone) {
        return smartPhoneRepository.save(smartPhone);
    }

    @Override
    public void delete(SmartPhone smartPhone) {
        smartPhoneRepository.delete(smartPhone);
    }

    @Override
    public void delete(Long id) {
        smartPhoneRepository.deleteById(id);
    }
}
