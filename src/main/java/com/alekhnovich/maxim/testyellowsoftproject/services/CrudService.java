package com.alekhnovich.maxim.testyellowsoftproject.services;

import com.alekhnovich.maxim.testyellowsoftproject.exceptions.EntityBeanNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrudService<E,K> {
    JpaRepository<E,K> getRepository();
    E addItem(E item);
    E getItem(K key) throws EntityBeanNotFoundException;
    E updateItem(E item);
    void deleteItemByKey(K key);
    void deleteItem(E item);
    List<E> getItems();
}
