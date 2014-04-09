package com.orders.ordermanagerdao;

import java.util.List;

import com.orders.ordermanagerdomain.Dish;

public interface DishDAO {
	
	public void addDish(Dish dish);
	
	public Dish getDish(Integer id);
	
	public void removeDish(Integer id);

	public List<Dish> listDish();
	
	public List<Dish> listDishForType(Integer typeId);
	
}
