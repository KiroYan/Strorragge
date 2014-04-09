package com.orders.ordermanagerweb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class OrderSortingModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Map<Integer, Map<String, Object>> sorts = 
			new HashMap<Integer, Map<String, Object>>();

	public Map<Integer, Map<String, Object>> getSorts() {
		return sorts;
	}
}
