package com.restaurant.ordermanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.restaurant.ordermanager.dao.DishTypeDAO;
import com.restaurant.ordermanager.domain.DishType;

@Service
public class DishTypeService {
	@Autowired
    private DishTypeDAO dishTypeDAO;
 
    @Transactional
    public void addDishType(DishType type) {
    	dishTypeDAO.addDishType(type);
    }
 
    @Transactional
    public List<DishType> listDishType() {
        return dishTypeDAO.listDishType();
    }
 
    @Transactional
    public void removeDishType(Integer id) {
    	dishTypeDAO.removeDishType(id);
    }
    
    @Transactional
    public DishType getDishType(Integer id) {
    	return dishTypeDAO.getDishType(id);
    }
}
