package com.orders.ordermanagerdao;

import java.util.List;

import com.orders.ordermanagerdomain.Dish;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DishDAOImpl implements DishDAO {
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
    private DishTypeDAO dishTypeDAO;
	
	public void addDish(Dish dish) {
		String hql = "FROM Dish D WHERE D.name = :theName";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("theName",dish.getName());
		Dish d = (Dish)query.uniqueResult();
		if (d == null) {
			sessionFactory.getCurrentSession().save(dish);
		}
	}
	
	public Dish getDish(Integer id) {
		String hql = "FROM Dish D WHERE D.id = :theId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("theId", id);
		Dish d = (Dish)query.uniqueResult();
		return d;
	}
	
    public void removeDish(Integer id) {
    	Dish dish = (Dish) sessionFactory.getCurrentSession().load(
    			Dish.class, id);
        if (null != dish) {
            sessionFactory.getCurrentSession().delete(dish);
        }
    }

	@SuppressWarnings("unchecked")
    public List<Dish> listDish() {
    	return sessionFactory.getCurrentSession().createQuery("from Dish").list();
    }
	
	@SuppressWarnings("unchecked")
	public List<Dish> listDishForType(Integer typeId) {
		String hql = "FROM Dish D WHERE D.type = :theType";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("theType", dishTypeDAO.getDishType(typeId));
		List<Dish> results = query.list();
		return results;		
	}

}
