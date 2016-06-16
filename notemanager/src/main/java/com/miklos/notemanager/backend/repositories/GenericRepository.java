package com.miklos.notemanager.backend.repositories;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.persistence.criteria.Predicate;

import com.miklos.notemanager.backend.entities.BaseModel;
import com.miklos.notemanager.backend.entities.User;

@Model
public interface GenericRepository<T extends BaseModel> {
    
    public void persist(T entity);
    
    public void save(T entity);
    
    public T findById(Long id, Class<T> entityClass);
    
    public void remove(T entity);
    
    public T refresh(T entity);
    
    public T findBySpecification(Specification<T> spec, Class<T> entityClass);
    
    public List<T> findListBySpecification(Specification<T> spec, Class<T> entityClass);
}
