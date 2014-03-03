package com.restaurant.ordermanager.dao;

import java.util.List;

import com.restaurant.ordermanager.domain.Dish;

public interface DishDAO {
	
	public void addDish(Dish dish);
	
	public Dish getDish(Integer id);
	
	public void removeDish(Integer id);

	public List<Dish> listDish();
	
	public List<Dish> listDishForType(Integer typeId);
	
}
