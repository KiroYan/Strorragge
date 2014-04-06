package com.task.newsportal.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.task.newsportal.domain.News;
import com.task.newsportal.domain.Tag;

@Repository
public class TagDAOImpl implements TagDAO {
	@Autowired
    private SessionFactory sessionFactory;
	
	public void addTag(Tag tag) {
		 sessionFactory.getCurrentSession().save(tag);
	}
	
	public Tag getTag(Integer id) {
		return (Tag) sessionFactory.getCurrentSession().load(Tag.class, id);
	}
	
	@SuppressWarnings("unchecked")
    public List<Tag> listTag() {
    	return sessionFactory.getCurrentSession().createQuery("from Tag")
                .list();
    }

    public void removeTag(Integer id) {
    	Tag tag = (Tag) sessionFactory.getCurrentSession().load(
    			Tag.class, id);
	        if (null != tag) {
	            sessionFactory.getCurrentSession().delete(tag);
	        }
    	
    }
    
    public List<News> getNews(Integer id) {
    	Tag tag = (Tag) sessionFactory.getCurrentSession().load(
    			Tag.class, id);
    	if (null != tag) {
    		List<News> news = tag.getNews();
    		news.size();
            return news;
        }
    	return null;
	}

	public Tag getTagByName(String tag) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Tag where name = :NAME");
		q.setString("NAME", tag);
		return (Tag) q.list().get(0);
	}
	
}
