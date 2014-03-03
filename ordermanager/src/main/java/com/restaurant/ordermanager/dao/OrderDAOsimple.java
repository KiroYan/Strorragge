package com.restaurant.ordermanager.dao;

import java.util.List;

import com.restaurant.ordermanager.domain.Order;
import com.restaurant.ordermanager.domain.User;
import com.restaurant.ordermanager.service.UserService;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOsimple implements OrderDAO{
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private UserService userService;
	
	public void addOrder(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }
	
	public Order getOrder(Integer id) {
		String hql = "FROM Order D WHERE D.id = :theId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("theId", id);
		Order o = (Order)query.uniqueResult();
		o.getDishes().get(0);
		return o;
	}

	@SuppressWarnings("unchecked")
    public List<Order> listOrder() {
		Authentication authentication = SecurityContextHolder.getContext().
					getAuthentication();
		String name = authentication.getName();
        User user = userService.getUser(name);
        
        String hql = "FROM Order o WHERE o.user = :theUser ORDER BY o.id DESC";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("theUser",user);
        return query.list();
    }
	
	public void removeOrder(Integer id) {
		Order order = (Order) sessionFactory.getCurrentSession().load(
				Order.class, id);
        if (null != order) {
            sessionFactory.getCurrentSession().delete(order);
        }

    }
}
