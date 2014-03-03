package com.restaurant.ordermanager.dao;

import java.util.List;

import com.restaurant.ordermanager.domain.DishType;

import org.hibernate.SessionFactory; 
import org.hibernate.Query; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DishTypeDAOsimple implements DishTypeDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public void addDishType(DishType type) {	
		String hql = "FROM DishType D WHERE D.name = :theName";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("theName",type.getName());
		DishType d = (DishType)query.uniqueResult();
		if (d == null) {
			sessionFactory.getCurrentSession().save(type);
		}
	}
	
	public DishType getDishType(Integer id) {
		DishType type = (DishType) sessionFactory.getCurrentSession().load(
    			DishType.class, id);
		return type;
	}
	
	public void removeDishType(Integer id) {
    	DishType type = (DishType) sessionFactory.getCurrentSession().load(
    			DishType.class, id);
        if (null != type) {
            sessionFactory.getCurrentSession().delete(type);
        }
    }

	@SuppressWarnings("unchecked")
    public List<DishType> listDishType() {
    	return sessionFactory.getCurrentSession().createQuery("from DishType").list();
    }

}
