package com.task.newsportal.service;

import java.util.List;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.newsportal.dao.TagDAO;
import com.task.newsportal.domain.News;
import com.task.newsportal.domain.Tag;

@Service
public class TagService {
	@Autowired
    private TagDAO tagDAO;
	
	@Transactional
	public void addTag(Tag tag) {
		tagDAO.addTag(tag);
	}
	
	@Transactional
	public Tag getTag(Integer id) {
		return tagDAO.getTag(id);
	}
	
	@Transactional
	public List<Tag> listTag() {
		return tagDAO.listTag();
	}
	
	@Transactional
	public void removeTag(Integer id) {
		tagDAO.removeTag(id);
	}

	@Transactional
	public List<News> getNews(Integer id) {
		return tagDAO.getNews(id);
	}

	@Transactional
	public Tag getTagByName(String tag) {
		return tagDAO.getTagByName(tag);
	}
}
