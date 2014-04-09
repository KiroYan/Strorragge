package com.orders.ordermanagerdao;

import java.util.List;

import com.orders.ordermanagerdomain.User;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
    private SessionFactory sessionFactory;
	
	public void addUser(User user) {
		String hql = "FROM User u WHERE u.login = :theLogin";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("theLogin",user.getLogin());
		User d = (User)query.uniqueResult();
		if (d == null) {
			sessionFactory.getCurrentSession().save(user);
		}
    }
	
	public User getUser(String name) {
		String hql = "FROM User u WHERE u.login = :theLogin";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("theLogin",name);
		User user = (User)query.uniqueResult();
		return user;
	}
	
	@SuppressWarnings("unchecked")
    public List<User> listUser() {
        return sessionFactory.getCurrentSession().createQuery("from User")
            .list();
    }
	
	public void removeUser(Integer id) {
		User user = (User) sessionFactory.getCurrentSession().load(
				User.class, id);
        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }

    }

	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().
				getAuthentication();
		String name = authentication.getName();
		User user = getUser(name);
		return user;
	}

}
