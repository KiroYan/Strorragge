package com.task.newsportal.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.task.newsportal.domain.*;
import com.task.newsportal.service.*;

@Controller
public class NewsportalController {
	@Autowired
    private TagService tagService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private CommentService commentService;
	
	@Autowired
    private NewsService newsService;
	
	@RequestMapping("/index")
    public String index() {
		return "redirect:/1";
    }
	
	@RequestMapping("/{number}")
    public String news(@PathVariable("number") Integer page, Map<String, Object> map) {
		List<News> newsList = newsService.listNews();
		List<News> portion = new ArrayList<News>();
		int start = (page - 1) * 10;
		int end = (newsList.size() < start + 10)? newsList.size() : start + 10;
		boolean hasNext = end < newsList.size();
        	boolean hasPrevious = start > 0; 
		
		for(int i = start; i < end; i++) {
			portion.add(newsList.get(i));
		}
        
        map.put("newsPortion", portion);
        map.put("nextPage", page + 1);
        map.put("previousPage", page - 1);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
       

        return "news";
    }
	
	@RequestMapping("/allnews")
    public String allNews(Map<String, Object> map) {
		List<News> news = newsService.listNews();
		
        map.put("newsPortion", news);
        map.put("nextPage", 0);
        map.put("previousPage", 0);
        map.put("hasNext", false);
        map.put("hasPrevious", false);       

        return "news";
    }
	
	
	@RequestMapping("/tag/{name}/{page}")
    public String newsByTag(@PathVariable("name") String tagName, @PathVariable("page") Integer page, Map<String, Object> map) {
	Tag tag = tagService.getTagByName(tagName);
	List<News> newsList = tagService.getNews(tag.getId());
	List<News> portion = new ArrayList<News>();
	int start = (page - 1) * 10;
	int end = (newsList.size() < start + 10)? newsList.size() : start + 10;
	boolean hasNext = end < newsList.size();
        boolean hasPrevious = start > 0;
        
        for(int i = start; i < end; i++) {
		portion.add(newsList.get(i));
	}
		     
        map.put("tag", tag);
        map.put("found", newsList.size());
        map.put("newsPortion", portion);
        map.put("nextPage", page + 1);
        map.put("previousPage", page - 1);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
		

        return "newsbytag";
    }
	
	
	@RequestMapping("/")
    public String home() {	
        return "redirect:/1";
    }
	
	@RequestMapping("/add")
    public String add(Map<String, Object> map) {
		map.put("news", new News());
		map.put("tagList", tagService.listTag());
			
        return "addnews";
    }
	
	@RequestMapping("/newtag")
    public String newTag(Map<String, Object> map) {
		map.put("tag", new Tag());		
		
        return "tag";
    }
	
	@RequestMapping(value = "/addTag", method = RequestMethod.POST)
    public String addTag(@ModelAttribute("tag") Tag tag) {
		tagService.addTag(tag);
				
        return "redirect:/add";
    }
	
	@RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") Integer id,Map<String, Object> map) {
		News news = newsService.getNews(id);
		map.put("news", news);
		map.put("tagList", tagService.listTag());
		
		return "update";
    }
	
	@RequestMapping(value = "/edit/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("news") News news) {
		List<Tag> tags = new ArrayList<Tag>();	
		List<Tag> newsTagsWithStrings = news.getTags();
		
		if(newsTagsWithStrings != null) {
			for(Tag tag:newsTagsWithStrings)
				tags.add(tagService.getTagByName(tag.getName()));
		}
		
		news.setTags(tags);		
		news.setUser(userService.getUser(news.getUser().getId()));			
		newsService.updateNews(news);
		
		return "redirect:/details/" + news.getId();
    }
	
	@RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public String addNews(@ModelAttribute("news") News news) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Tag> tags = new ArrayList<Tag>();	
		List<Tag> newsTagsWithStrings = news.getTags();
		
		if(newsTagsWithStrings != null) {
			for(Tag tag:newsTagsWithStrings)
				tags.add(tagService.getTagByName(tag.getName()));
		}
		
		news.setTags(tags);	
		news.setUser(userService.getUserByLogin(auth.getName()));
		news.setDate(new Timestamp(new Date().getTime()));
		
        newsService.addNews(news);

        return "redirect:/index";
    }
	
	@RequestMapping(value = "/details/{id}")
	 public String showDetalis(@PathVariable("id") Integer id, Map<String, Object> map) {
		News news = newsService.getNews(id);
		
		map.put("news", news);
		map.put("comment", new Comment());
		map.put("comments", news.getComments());
			
		return "details";
    }
	
	@RequestMapping(value = "/details/{id}/addComment", method = RequestMethod.POST)
    public String addComment(@PathVariable("id") Integer id, @ModelAttribute("comment") Comment comment) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		comment.setUser(userService.getUserByLogin(auth.getName()));
		comment.setDate(new Timestamp(new Date().getTime()));
		comment.setNews(newsService.getNews(id));
		
		commentService.addComment(comment);


        return "redirect:/details/" + id;
    }
	
	 @RequestMapping("/{newsID}/deletecomments/{id}")
	 public String deleteComment(@PathVariable("newsID") Integer newsId, @PathVariable("id") Integer id) {
	        commentService.removeComment(id);

	        return "redirect:/details/" + newsId;
	 }
	 
	 @RequestMapping("/delete/{id}")
	 public String deleteNews(@PathVariable("id") Integer id) {
	        newsService.removeNews(id);

	        return "redirect:/1";
	 }
	
}
