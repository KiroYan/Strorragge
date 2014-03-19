package com.task.newsportal.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.task.newsportal.domain.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	

	@Autowired
    private SessionFactory sessionFactory;
	
	public void addUser(User user) {
		 sessionFactory.getCurrentSession().save(user);
	}
	
	@SuppressWarnings("unchecked")
    public List<User> listUser() {
    	return sessionFactory.getCurrentSession().createQuery("from TAGS")
                .list();
    }

    public void removeUser(Integer id) {
    	User user = (User) sessionFactory.getCurrentSession().load(
    			User.class, id);
	        if (null != user) {
	            sessionFactory.getCurrentSession().delete(user);
	        }
   }
   
    public User getUserByLogin(String login) {
    	Query q = sessionFactory.getCurrentSession().createQuery("from User where login=:LOGIN");
    	q.setString("LOGIN", login); 	
        return (User) q.list().get(0);
	}

	public User getUser(Integer id) {
		
		return (User) sessionFactory.getCurrentSession().load(User.class, id);
	}

	
}
