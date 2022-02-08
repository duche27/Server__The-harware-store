package com.guille.hardware.store.commons;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface GenericService<T, ID extends Serializable> {

    T save(T entity);

    T get(ID id);

    Set<T> getAll();

    Long totalNumber();

    T delete(ID id);

    void deleteAll();
}
