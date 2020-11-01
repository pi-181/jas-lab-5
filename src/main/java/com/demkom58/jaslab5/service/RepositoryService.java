package com.demkom58.jaslab5.service;

import com.demkom58.jaslab3.model.ObservableEntity;
import com.demkom58.jaslab3.repo.CrudRepository;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class RepositoryService<T extends ObservableEntity> implements SimpleService<T> {
    private final CrudRepository<Integer, T> repository;
    private final Supplier<T[]> emptyArraySupplier;

    public RepositoryService(@NotNull final CrudRepository<Integer, T> repository, Supplier<T[]> emptyArraySupplier) {
        this.repository = repository;
        this.emptyArraySupplier = emptyArraySupplier;
    }

    @Override
    public boolean add(T p) {
        return repository.add(p);
    }

    @Override
    public boolean delete(int id) {
        return repository.removeById(id);
    }

    @Override
    public T get(int id) {
        return repository.getById(id);
    }

    @Override
    public T[] getAll() {
        return repository.getAll().toArray(emptyArraySupplier.get());
    }
}
