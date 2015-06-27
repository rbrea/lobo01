package com.icetea.manager.pagodiario.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;

import com.icetea.manager.pagodiario.dao.pagination.PaginationContainer;
import com.icetea.manager.pagodiario.model.Model;

@Transactional
public abstract class BasicDaoImpl<E extends Model>
    implements BasicDao<E> {

    @Inject
    private SessionFactory sessionFactory;

    public BasicDaoImpl() {
        super();
    }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
    
    protected final Criteria createCriteria() {
		return this.getCurrentSession().createCriteria(this.getEntityClass());
	}

    @Override
    public boolean save(E e) {
        if (e == null) {
            return false;
        }
        this.getCurrentSession().save(e);

        return true;
    }

    @Override
    public boolean saveOrUpdate(E e) {
        return this.saveOrUpdate(e, false);
    }

    @Override
    public boolean saveOrUpdate(E e, boolean flush) {
        if (e == null) {
            return false;
        }
        this.getCurrentSession().saveOrUpdate(e);

        if (flush) {
            this.getCurrentSession().flush();
        }
        return true;
    }

    @Override
    public boolean delete(E e) {
        if (e == null) {
            return false;
        }
        this.getCurrentSession().delete(e);

        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findByCriteria(Criterion criterion) {
        Criteria criteria = this.getCurrentSession().createCriteria(this.getEntityClass());
        criteria.add(criterion);

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E findUniqueByCriteria(Criterion criterion) {
        Criteria criteria = this.getCurrentSession().createCriteria(this.getEntityClass());
        criteria.add(criterion);

        return (E) criteria.uniqueResult();
    }

    protected abstract Class<E> getEntityClass();

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findAll() {
        Criteria criteria = this.getCurrentSession().createCriteria(this.getEntityClass());

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findAll(Integer maxResults) {
        Criteria criteria = this.getCurrentSession().createCriteria(this.getEntityClass());
        if (maxResults != null && maxResults > 0) {
            criteria.setMaxResults(maxResults);
        }

        return criteria.list();
    }

    protected void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean refresh(E e) {
    	this.getCurrentSession().refresh(e);
    	
    	return true;
    }
    
    @Override
    public PaginationContainer findAll(Integer pageNumber, Integer pageSize) {
    	
    	PaginationContainer paginationContainer = new PaginationContainer();
    	
    	Number totalRows = (Number) this.createCriteria().setProjection(
    			Projections.rowCount()).uniqueResult();

    	if(totalRows == null || totalRows.intValue() <= 0){
    		return paginationContainer;
    	}
    	
        Criteria criteria = this.getCurrentSession().createCriteria(this.getEntityClass());
        if(pageNumber == null){
        	pageNumber = 1;
        }
        if(pageSize == null){
        	pageSize = PAGE_SIZE_DEFAULT; 
        }
        criteria.setFirstResult((pageNumber-1) * pageSize);
        criteria.setMaxResults(pageSize);

        paginationContainer.setList(criteria.list());
        paginationContainer.setTotalRows(totalRows.intValue());
        
        return paginationContainer;
    }

    protected Integer count(Criterion criterion){
    	Criteria criteria = this.createCriteria();
    	criteria.add(criterion);
    	criteria.setProjection(Projections.rowCount());
    	
    	Number n = (Number)criteria.uniqueResult();
    	
		return (n != null) ? n.intValue() : null;
    }
    
}
