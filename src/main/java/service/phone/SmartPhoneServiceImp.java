package service.phone;

import model.SmartPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repositories.SmartPhoneRepository;
import service.phone.exception.NotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SmartPhoneServiceImp implements SmartPhoneService {
    private final SmartPhoneRepository smartPhoneRepository;

    @Autowired
    public SmartPhoneServiceImp(SmartPhoneRepository smartPhoneRepository) {
        this.smartPhoneRepository = smartPhoneRepository;
    }

    @Override
    public List<SmartPhone> findAll() throws NotFoundException {
        List<SmartPhone> smartPhones = new LinkedList<>();
        Iterable<SmartPhone> iterable = smartPhoneRepository.findAll();
        for (SmartPhone smartPhone : iterable) {
            smartPhones.add(smartPhone);
        }
        if (smartPhones.isEmpty()) {
            throw new NotFoundException();
        }
        return smartPhones;
    }

    @Override
    public List<SmartPhone> findAll(Sort sort) throws NotFoundException {
        List<SmartPhone> smartPhones = new LinkedList<>();
        Iterable<SmartPhone> iterable = smartPhoneRepository.findAll(sort);
        for (SmartPhone smartPhone : iterable) {
            smartPhones.add(smartPhone);
        }
        if (smartPhones.isEmpty()) {
            throw new NotFoundException();
        }
        return smartPhones;
    }

    @Override
    public Page<SmartPhone> findAll(Pageable pageable) throws NotFoundException {
        Page<SmartPhone> page = smartPhoneRepository.findAll(pageable);
        if (page.isEmpty()) {
            throw new NotFoundException();
        }
        return page;
    }

    @Override
    public SmartPhone findOne(Long id) throws NotFoundException {
        Optional<SmartPhone> optional = smartPhoneRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundException();
        }
        return optional.get();
    }

    @Override
    public SmartPhone save(SmartPhone smartPhone) throws InvalidDataAccessApiUsageException {
        return smartPhoneRepository.save(smartPhone);
    }

    @Override
    public void delete(SmartPhone smartPhone) throws InvalidDataAccessApiUsageException {
        smartPhoneRepository.delete(smartPhone);
    }

    @Override
    public void delete(Long id) throws InvalidDataAccessApiUsageException {
        smartPhoneRepository.deleteById(id);
    }
}
