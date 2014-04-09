package com.orders.ordermanagerweb;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.orders.ordermanagerdomain.Dish;
import com.orders.ordermanagerdomain.Order;
import com.orders.ordermanagerservice.DishTypeService;
import com.orders.ordermanagerservice.DishService;
import com.orders.ordermanagerservice.OrderService;
import com.orders.ordermanagerservice.UserService;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private OrderSortingModel orderSortingModel;
	
	private Order currentOrder = new Order();
    
    @RequestMapping("/")
    public String home() {
    	
        return "redirect:/listOrders/1";
    }
    
    @RequestMapping("/index")
    public String index() {
    	
        return "redirect:/listOrders/1";
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
    
    @RequestMapping("/listOrders/{field}/{page}")
    public String listOrders(@PathVariable("page") Integer page, 
    						 @PathVariable("field") String field, Map<String, Object> map) {    	
    	int begin = (page - 1) * 10;
    	int size = 10;
    	boolean hasNext = orderService.ordersCount() > (begin + size) ? true : false;   	
    	boolean hasPrevious = 0 < begin ? true : false;	
    	Map<Integer, Map<String, Object>> sorts = orderSortingModel.getSorts();
    	Map<String, Object> m;
    	boolean asc = true;
    	
    	if(!sorts.containsKey(page)) {
    		m = new HashMap<String, Object>();
    		m.put("asc", asc);
    		m.put("fieldSorted", field);
    		m.put("idSorted", field.equals("id"));
            m.put("dateSorted", field.equals("date"));
            sorts.put(page, m);
    	} else {
    		m = sorts.get(page);
    		if(m.get("fieldSorted").equals(field)) {
    			asc = !(Boolean)m.get("asc");
    			m.put("asc", asc);
    		} else {
    			m.put("asc", asc);
    			m.put("fieldSorted", field);
    			m.put("idSorted", field.equals("id"));
                m.put("dateSorted", field.equals("date"));
    		}
    	}
 	
        map.put("orderList", orderService.listOrder(begin, size, field, (Boolean) m.get("asc")));
        map.put("page", page);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious); 
        map.putAll(m);      
        
        return "listorders";
    }    
    
    @RequestMapping("/listOrders/{page}")
    public String listOrders(@PathVariable("page") Integer page, Map<String, Object> map) {  
    	int begin = (page - 1) * 10;
    	int size = 10;
    	boolean hasNext = orderService.ordersCount() > (begin + size) ? true : false;   	
    	boolean hasPrevious = 0 < begin ? true : false;	
    	Map<Integer, Map<String, Object>> sorts = orderSortingModel.getSorts();
    	
    	boolean asc = false;
      	
    	if(!sorts.containsKey(page)) {
    		map.put("orderList", orderService.listOrder(begin, size));
    		map.put("asc", asc);
    		map.put("idSorted", true);
    	} else {  		
    		map.put("orderList", orderService.listOrder(begin, size, 
    				(String) sorts.get(page).get("fieldSorted"), (Boolean) sorts.get(page).get("asc")));
    		map.putAll(sorts.get(page));
    	}
    	
        map.put("page", page);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);		
    	
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
		
        if(!currentOrder.getDishes().isEmpty()) {
        	orderService.addOrder(currentOrder);   	
    		currentOrder = new Order();
        }      

        return "redirect:/listOrders/1";
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

        return "redirect:/listOrders/1";
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
