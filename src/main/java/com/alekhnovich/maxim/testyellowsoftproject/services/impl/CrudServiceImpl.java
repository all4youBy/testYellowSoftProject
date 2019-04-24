package com.alekhnovich.maxim.testyellowsoftproject.services.impl;

import com.alekhnovich.maxim.testyellowsoftproject.exceptions.EntityBeanNotFoundException;
import com.alekhnovich.maxim.testyellowsoftproject.services.CrudService;

import java.util.List;

public abstract class CrudServiceImpl<E,K> implements CrudService<E,K>{

    @Override
    public E addItem(E item) {
        return getRepository().save(item);
    }

    @Override
    public E getItem(K key) throws EntityBeanNotFoundException {
        return getRepository().findById(key).orElseThrow(()-> new EntityBeanNotFoundException("No bean with such id"));
    }

    @Override
    public void deleteItemByKey(K key) {
        getRepository().deleteById(key);
    }

    @Override
    public void deleteItem(E item) {
            getRepository().delete(item);
    }

    @Override
    public List<E> getItems() {
            return getRepository().findAll();
    }
}
