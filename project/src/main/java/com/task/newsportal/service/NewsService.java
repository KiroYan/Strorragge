package com.task.newsportal.service;

import java.sql.Timestamp;
import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.newsportal.dao.NewsDAO;
import com.task.newsportal.domain.News;

@Service
public class NewsService {
	@Autowired
	private NewsDAO newsDAO;
	
	@Transactional
	public void addNews(News news) {
		newsDAO.addNews(news);
	}
	@Transactional
	public List<News> listNews() {
		return newsDAO.listNews();
	}
	@Transactional
	public void removeNews(Integer id) {
		newsDAO.removeNews(id);
	}
	@Transactional
	public List<News> listNewsForTag(Integer typeId) {
		return newsDAO.listNewsForTag(typeId);
	}
	@Transactional
	public List<News> listNewsForInterval(Timestamp begin, Timestamp end) {
		return newsDAO.listNewsForInterval(begin, end);
	}
	@Transactional
	public News getNews(Integer id) {
		return newsDAO.getNews(id);
	}
	@Transactional
	public void updateNews(News news) {
		 newsDAO.updateNews(news);
	}

}
