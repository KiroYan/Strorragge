package com.task.newsportal.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.task.newsportal.domain.News;
import com.task.newsportal.service.TagService;

@Repository
public class NewsDAOImpl implements NewsDAO {
	
	

	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private TagService tagService;
	
	public void addNews(News news) {
		sessionFactory.getCurrentSession().save(news);
	}
	
	@SuppressWarnings("unchecked")
	public List<News> listNews() {
		return sessionFactory.getCurrentSession().createQuery("from News order by date desc").list();		
	}
	
	public void updateNews(News news) {
		sessionFactory.getCurrentSession().update(news);
	}
	
	public void removeNews(Integer id) {
		News news = (News) sessionFactory.getCurrentSession().load(News.class, id);
		if (null != news) {
			sessionFactory.getCurrentSession().delete(news);
	    }	
	}

	public List<News> listNewsForTag(Integer typeId) {
		return tagService.getNews(typeId);
	}

	@SuppressWarnings("unchecked")
	public List<News> listNewsForInterval(Timestamp begin, Timestamp end) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(News.class);
		cr.add(Restrictions.between("date", begin, end));
		List<News> result = cr.list();
		return result;
	}
	
	public News getNews(Integer id) {
		Query q = sessionFactory.getCurrentSession().createQuery("from News where id=:ID");
		q.setParameter("ID", id);
		News news = (News) q.list().get(0);
		
		news.getComments().size();	
		return news;
	}

	
}
