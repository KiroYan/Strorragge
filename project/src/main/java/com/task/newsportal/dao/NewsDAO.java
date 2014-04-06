package com.task.newsportal.dao;

import java.sql.Timestamp;
import java.util.List;

import com.task.newsportal.domain.News;

public interface NewsDAO {
	
	public void addNews(News news);
	
	public List<News> listNews();
	
	public void removeNews(Integer id);
	
	public List<News> listNewsForTag(Integer typeId);
	
	public List<News> listNewsForInterval(Timestamp begin, Timestamp end);
	
	public News getNews(Integer id);
	
	public void updateNews(News news);
}
