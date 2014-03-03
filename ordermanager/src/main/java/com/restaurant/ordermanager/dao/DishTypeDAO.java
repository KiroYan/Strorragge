package com.restaurant.ordermanager.dao;

import java.util.List;
import com.restaurant.ordermanager.domain.DishType;

public interface DishTypeDAO {
	
	public void addDishType(DishType type);
	
	public DishType getDishType(Integer id);
	
	public void removeDishType(Integer id);
	
	public List<DishType> listDishType();
	 
}
