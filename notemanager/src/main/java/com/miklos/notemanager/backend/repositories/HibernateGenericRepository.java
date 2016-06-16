package com.miklos.notemanager.backend.repositories;


import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.UserTransaction;

import com.miklos.notemanager.backend.entities.BaseModel;
import com.miklos.notemanager.backend.entities.User;


@Model
@Default
@Dependent

public class HibernateGenericRepository<T extends BaseModel> implements GenericRepository<T> {

	@PersistenceContext(unitName="notemanager")
	EntityManager entityManager;
	

	
	
	@Override
	public void persist(T entity) {
		//EntityManager entityManager = factory.createEntityManager();
		try {
			UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			transaction.begin();
			entityManager.joinTransaction();
			entityManager.persist(entity);
			transaction.commit();
		} catch (NotSupportedException e) {
			
		} catch (SystemException e) {
			
		} catch (IllegalStateException e) {
			
		} catch (SecurityException e) {
			
		} catch (HeuristicMixedException e) {
			
		} catch (HeuristicRollbackException e) {
			
		} catch (RollbackException e) {
			
		} catch (NamingException e) {
			
		}

	}

	@Override
	public void save(T entity) {
		//EntityManager entityManager = factory.createEntityManager();
		try {
			UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			transaction.begin();
			entityManager.joinTransaction();
			entityManager.merge(entity);
			transaction.commit();
		} catch (NotSupportedException e) {
			
		} catch (SystemException e) {
			
		} catch (IllegalStateException e) {
			
		} catch (SecurityException e) {
			
		} catch (HeuristicMixedException e) {
			
		} catch (HeuristicRollbackException e) {
			
		} catch (RollbackException e) {
			
		} catch (NamingException e) {
			
		}
	}

	@Override
	public void remove(T entity) {
		//EntityManager entityManager = factory.createEntityManager();
		entityManager.remove(entity);
	}

	@Override
	public T findById(Long id, Class<T> entityClass) {
		//EntityManager entityManager = factory.createEntityManager();
		return entityManager.find(entityClass, id);
	}

	@Override
	public T findBySpecification(Specification<T> spec, Class<T> entityClass) {
		//EntityManager entityManager = factory.createEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(entityClass);
		Root<T> root = q.from(entityClass);
		ParameterExpression<Integer> p = cb.parameter(Integer.class);
		CriteriaQuery<T> criteriaQuery = q.select(root);
		criteriaQuery.where(spec.toPredicate(root, criteriaQuery, cb));
		TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);

		if(typedQuery.getResultList().size()>0) {
			return typedQuery.getResultList().get(0);
		}else{
			return null;
		}

	}

	@Override
	public List<T> findListBySpecification(Specification<T> spec, Class<T> entityClass) {
		//EntityManager entityManager = factory.createEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(entityClass);
		Root<T> root = q.from(entityClass);
		ParameterExpression<Integer> p = cb.parameter(Integer.class);
		CriteriaQuery<T> criteriaQuery = q.select(root);
		criteriaQuery.where(spec.toPredicate(root, criteriaQuery, cb));
		TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public T refresh(T entity) {
		//EntityManager entityManager = factory.createEntityManager();
		try {
			UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			boolean managed = entityManager.contains(entity);
			transaction.begin();
			entityManager.joinTransaction();
			T merged = entityManager.merge(entity);
			entityManager.refresh(merged);
			transaction.commit();
			return merged;
			
			
		} catch (NotSupportedException e) {
			
		} catch (SystemException e) {
			
		} catch (IllegalStateException e) {
			
		} catch (SecurityException e) {
			
		} catch (HeuristicMixedException e) {
			
		} catch (HeuristicRollbackException e) {
			
		} catch (RollbackException e) {
			
		} catch (NamingException e) {
			
		}
		return null;
	}

}
