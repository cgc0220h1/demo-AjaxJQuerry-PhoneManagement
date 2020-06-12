package service;

import model.SmartPhone;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import service.phone.exception.NotFoundException;

import java.util.List;

public interface GenericService<T> {
    List<T> findAll() throws NotFoundException;

    List<T> findAll(Sort sort) throws NotFoundException;

    Page<T> findAll(Pageable pageable) throws NotFoundException;

    T findOne(Long id) throws NotFoundException;

    T save(T entity) throws InvalidDataAccessApiUsageException;

    void delete(T entity) throws InvalidDataAccessApiUsageException;

    void delete(Long id) throws InvalidDataAccessApiUsageException;
}
