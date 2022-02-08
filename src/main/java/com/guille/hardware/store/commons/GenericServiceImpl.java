package com.guille.hardware.store.commons;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {

    public abstract CrudRepository<T, ID> getDao();

    @Override
    public T save(T entity) {

        return getDao().save(entity);
    }

    @Override
    public T get(ID id) {

        Optional<T> obj = getDao().findById(id);
        if (obj.isPresent()) return obj.get();

        return null;
    }

    @Override
    public Set<T> getAll() {

        Set<T> results = new HashSet<>();
        getDao().findAll().forEach(r -> results.add(r));

        return results;
    }

    @Override
    public Long totalNumber() {

        return getDao().count();
    }

    @Override
    public T delete(ID id) {

        T t = get(id);

        if (!Objects.isNull(t)) {
            getDao().deleteById(id);
            return t;
        }

        return null;
    }

    @Override
    public void deleteAll() {

        getDao().deleteAll();
    }
}
