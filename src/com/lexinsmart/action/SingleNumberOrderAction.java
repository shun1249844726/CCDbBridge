package com.lexinsmart.action;

import java.sql.Timestamp;

import com.lexinsmart.model.SingleNumberOrder;
import com.lexinsmart.service.SinglenumberOrderService;

public class SingleNumberOrderAction {

	public static void main(String[] args) {
		SingleNumberOrder order = new SingleNumberOrder();
		order.setCarno("苏EE0001");
		order.setHight(0);
		order.setSingleno("1030t11111");
		order.setWeightimes(1);
		order.setOrderno(0);
		order.setDepart("Depart1");
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());
		order.setCreatetime(nowtime);
		order.setTtype("工程车辆");
		
		SinglenumberOrderService orderService = new SinglenumberOrderService();
		orderService.addOrder(order);
		
		orderService.release();
		
	}
}
