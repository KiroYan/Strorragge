package com.orders.ordermanagerservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orders.ordermanagerdao.DishDAO;
import com.orders.ordermanagerdomain.Dish;

@Service
public class DishService {
	@Autowired
    private DishDAO dishDAO;
 
    @Transactional
    public void addDish(Dish dish) {
    	dishDAO.addDish(dish);
    }
 
    @Transactional
    public List<Dish> listDish() {
        return dishDAO.listDish();
    }
    
    @Transactional
    public Dish getDish(Integer id) {
    	return dishDAO.getDish(id);
    }
    
    @Transactional
    public List<Dish> listDishForType(Integer typeId) {
    	return dishDAO.listDishForType(typeId);
    }
 
    @Transactional
    public void removeDish(Integer id) {
    	dishDAO.removeDish(id);
    }
}
