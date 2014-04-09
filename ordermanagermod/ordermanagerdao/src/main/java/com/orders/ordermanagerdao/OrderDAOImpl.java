package com.orders.ordermanagerdao;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.orders.ordermanagerdomain.Order;
import com.orders.ordermanagerdomain.OrderComparator;
import com.orders.ordermanagerdomain.User;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl implements OrderDAO{
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDAO;
	
	public void addOrder(Order order) {
		order.setUser(userDAO.getCurrentUser());
		order.setDate(new Timestamp(new Date().getTime()));	
		
        sessionFactory.getCurrentSession().save(order);
    }
	
	public Order getOrder(Integer id) {
		String hql = "FROM Order D WHERE D.id = :theId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("theId", id);
		Order o = (Order)query.uniqueResult();
		o.getDishes().size();
		return o;
	}

	@SuppressWarnings("unchecked")
    public List<Order> listOrder() {
        User user = userDAO.getCurrentUser();    
        String hql = "FROM Order o WHERE o.user = :theUser ORDER BY o.id DESC";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("theUser", user);
        
        return query.list();
    }
	
	public void removeOrder(Integer id) {
		Order order = (Order) sessionFactory.getCurrentSession().load(
				Order.class, id);
        if (null != order) {
            sessionFactory.getCurrentSession().delete(order);
        }

    }

	@SuppressWarnings("unchecked")
	public List<Order> listOrder(int begin, int size) {
		User user = userDAO.getCurrentUser();
		String hql = "FROM Order o WHERE o.user = :theUser ORDER BY o.id DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("theUser", user);
		query.setFirstResult(begin);
		query.setMaxResults(size);
			
		return query.list();
	}

	public Long ordersCount() {
		User user = userDAO.getCurrentUser();
		String hql = "SELECT COUNT(*) FROM Order o WHERE o.user = :theUser";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("theUser", user);
		
		@SuppressWarnings("unchecked")
		List<Long> orders = query.list();
		
		if(orders != null) {
			return orders.get(0);
		}
		
		return 0L;
	}

	@SuppressWarnings("unchecked")
	public List<Order> listOrder(int begin, int size, String field, boolean asc) {
		if(!field.equals("id") && !field.equals("date"))
			field = "id";
/*				
		User user = userDAO.getCurrentUser();
		//long total = ordersCount();
		//long end = (begin + size > total) ? total - 1 : begin + size - 1;	
		
		String hql;
		if(asc)
			hql = "FROM Order o WHERE o.user = :theUser ORDER BY id ASC";
		else
			hql = "FROM Order o WHERE o.user = :theUser ORDER BY date DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		query.setParameter("theUser", user);
		//query.setString("field", field);
		//query.setString("order", asc ? "ASC" : "DESC");
		query.setFirstResult(begin);
		query.setMaxResults(size);
		
		return query.list();	
				
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);		
		criteria.add(Restrictions.eq("user", user));
		//criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		criteria.setFirstResult(begin);
		criteria.setMaxResults(size);
/*		
		Order first = (Order) criteria.list().get(0);
		
		criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);		
		criteria.add(Restrictions.eq("user", user));
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		criteria.setFirstResult((int)end);
		criteria.setMaxResults(1);
		
		Order last = (Order) criteria.list().get(0);
		
		int firstId = first.getId() < last.getId() ? first.getId() : last.getId();
		int lastId = first.getId() < last.getId() ? last.getId() : first.getId();		
		
		criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);	
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.between("id", firstId, lastId));
		
		if(asc) {
			criteria.addOrder(org.hibernate.criterion.Order.asc(field));
		} else {
			criteria.addOrder(org.hibernate.criterion.Order.desc(field));
		}			
		
		return criteria.list();*/
		
		List<Order> orders = listOrder(begin, size);
		Comparator<Order> comp;
		
		if(asc) {
			comp = OrderComparator.getComparator(field);
		} else {
			comp = OrderComparator.getReverseComparator(field);
		}
		
		Collections.sort(orders, comp);
		return orders;

	}
}
