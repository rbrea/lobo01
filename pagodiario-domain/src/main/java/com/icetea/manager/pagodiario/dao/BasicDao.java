package com.icetea.manager.pagodiario.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;

import com.icetea.manager.pagodiario.dao.pagination.PaginationContainer;
import com.icetea.manager.pagodiario.model.Model;

public interface BasicDao<E extends Model> {

	Integer PAGE_SIZE_DEFAULT = 10;
	
    boolean save(E e);

    boolean saveOrUpdate(E e);

    boolean delete(E e);

    List<E> findByCriteria(Criterion criterion);

    List<E> findAll();

    List<E> findAll(Integer maxResults);

    E findUniqueByCriteria(Criterion criterion);

    boolean saveOrUpdate(E e, boolean flush);
    
    boolean refresh(E e);

	PaginationContainer findAll(Integer pageNumber, Integer pageSize);

}
