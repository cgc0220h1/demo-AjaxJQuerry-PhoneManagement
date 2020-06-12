package service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GenericService<T> {
    List<T> findAll();

    List<T> findAll(Sort sort);

    List<T> findAll(Pageable pageable);

    T findOne(Long id);

    T save(T entity);

    void delete(T entity);

    void delete(Long id);
}
