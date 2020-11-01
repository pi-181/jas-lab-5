package com.demkom58.jaslab3.repo;

import com.demkom58.jaslab3.model.ObservableEntity;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collection;

public class CommonRepository<K, V extends ObservableEntity> implements CrudRepository<K, V> {
    private final EntityManager entityManager;

    private final Query selectAll;
    private final Query selectById;
    private final Query deleteById;

    private final String tableName;

    public CommonRepository(EntityManager entityManager, String tableName, String idColumnName) {
        this.entityManager = entityManager;
        this.tableName = tableName;

        this.selectAll = entityManager.createQuery("FROM " + tableName);
        this.selectById = entityManager.createQuery("FROM " + tableName + " WHERE " + idColumnName + " = :id");
        this.deleteById = entityManager.createQuery("DELETE FROM " + tableName + " WHERE " + idColumnName + " = :id");
    }

    @Override
    @SuppressWarnings("unchecked")
    public @NotNull Collection<V> getAll() {
        return selectAll.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public @Nullable V getById(@NotNull K key) {
        try {
            return (V) selectById.setParameter("id", key).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeById(@NotNull K key) {
        final EntityTransaction transaction = entityManager.getTransaction();
        boolean result = false;
        try {
            transaction.begin();
            result = deleteById.setParameter("id", key).executeUpdate() != 0;
        } finally {
            transaction.commit();
        }

        return result;
    }

    @Override
    public void save(@NotNull V value) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            final boolean attached = value.getId() != null && entityManager.find(value.getClass(), value.getId()) != null;
            System.out.println(value);
            if (attached)
                entityManager.merge(value);
            else
                entityManager.unwrap(Session.class).save(value);
        } finally {
            transaction.commit();
        }
    }

    @NotNull
    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String toString() {
        return getTableName();
    }
}
