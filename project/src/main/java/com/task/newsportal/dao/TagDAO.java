package com.task.newsportal.dao;

import java.util.List;





import com.task.newsportal.domain.News;
import com.task.newsportal.domain.Tag;

public interface TagDAO {
	
	public void addTag(Tag tag);
	
	public Tag getTag(Integer id);
	
	public Tag getTagByName(String tag);

    public List<Tag> listTag();

    public void removeTag(Integer id);
    
    public List<News> getNews(Integer id);

}
