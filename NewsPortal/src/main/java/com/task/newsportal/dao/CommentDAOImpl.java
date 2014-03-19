package com.task.newsportal.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.task.newsportal.domain.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {
	@Autowired
    private SessionFactory sessionFactory;
	
	public void addComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }
	
	@SuppressWarnings("unchecked")
    public List<Comment> listComment() {

        return sessionFactory.getCurrentSession().createQuery("from COMMENTS")
            .list();
    }
	
	 public void removeComment(Integer id) {
		 Comment comment = (Comment) sessionFactory.getCurrentSession().load(
				 Comment.class, id);
	        if (null != comment) {
	            sessionFactory.getCurrentSession().delete(comment);
	        }

	    }
	
	
	

}
