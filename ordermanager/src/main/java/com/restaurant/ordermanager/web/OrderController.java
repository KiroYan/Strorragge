package com.restaurant.ordermanager.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.restaurant.ordermanager.domain.Dish;
import com.restaurant.ordermanager.domain.Order;
import com.restaurant.ordermanager.domain.User;
import com.restaurant.ordermanager.service.DishTypeService;
import com.restaurant.ordermanager.service.DishService;
import com.restaurant.ordermanager.service.OrderService;
import com.restaurant.ordermanager.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
	
	@Autowired
    private DishTypeService dishTypeService;

	@Autowired
    private DishService dishService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	private Order currentOrder = new Order();
	
    @RequestMapping("/index")
    public String index() {
    	
        return "home";
    }
    
    @RequestMapping("/")
    public String home() {
    	
        return "redirect:/index";
    }
	
	@RequestMapping("/newOrder")
    public String newOrder(Map<String, Object> map) {  	
		currentOrder = new Order();
    	map.put("order", currentOrder);
        map.put("dish", new Dish());
        map.put("dishTypeList", dishTypeService.listDishType());
        map.put("currentOrder", currentOrder.getDishes());
    	
       return "createorder";
    }
	
    @RequestMapping("/editOrder")
    public String editOrder(Map<String, Object> map) {  	
        map.put("order", currentOrder);
        map.put("dish", new Dish());
        map.put("dishTypeList", dishTypeService.listDishType());
        map.put("currentOrder", currentOrder.getDishes());
        map.put("totalPrice", currentOrder.getTotalPrice());
        
        return "createorder";
    }
    
    @RequestMapping("/listOrders")
    public String listOrders(Map<String, Object> map) { 
        map.put("orderList", orderService.listOrder());
        
        return "listorders";
    }
    
    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String addItem(@ModelAttribute("dish") Dish dish) {
    	if(dish.getId() != -1) {
    		Dish ddish = dishService.getDish(dish.getId());
    		currentOrder.getDishes().add(ddish);
    	}

        return "redirect:/editOrder";
    }
    
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public String addOrder(@ModelAttribute("order") Order order) {
    	Authentication authentication = SecurityContextHolder.getContext().
    				getAuthentication();
    	String name = authentication.getName();
    	User user = userService.getUser(name);
        currentOrder.setUser(user);
        
        Date date= new Date();
        currentOrder.setDate(new Timestamp(date.getTime()));
        
        if(!currentOrder.getDishes().isEmpty()) {
        	orderService.addOrder(currentOrder);   	
    		currentOrder = new Order();
        }

        return "redirect:/index";
    }
    
    @RequestMapping("/viewOrder/{orderId}")
    public String viewOrder(@PathVariable("orderId") Integer orderId, Map<String, Object> map) {
    	Order order = orderService.getOrder(orderId);
    	map.put("order", order);
        map.put("orderItems", order.getDishes());
        map.put("totalPrice", order.getTotalPrice());
    	
        return "vieworder";
    }
    
    @RequestMapping("/deleteItem/{dishId}")
    public String deleteItem(@PathVariable("dishId") Integer dishId) {
    	Dish dish = dishService.getDish(dishId);
    	currentOrder.getDishes().remove(dish);

        return "redirect:/editOrder";
    }
    
    @RequestMapping("/deleteOrder/{orderId}")
    public String deleteOrder(@PathVariable("orderId") Integer orderId) {
    	orderService.removeOrder(orderId);

        return "redirect:/listOrders";
    }

    @RequestMapping(value = "/getDishes", method = RequestMethod.POST)
    public @ResponseBody String onSelectChange(@RequestBody String newInput) throws JsonGenerationException, JsonMappingException, IOException {
    	newInput = newInput.replaceAll("\\D", "");
    	
    	List<Dish> dishList = dishService.listDishForType(Integer.parseInt(newInput));
    	
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.registerModule(new Hibernate4Module());
        String json = mapper.writeValueAsString(dishList);
        
    	return json;
    }

}
