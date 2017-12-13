package com.lexinsmart.action;

import java.sql.Timestamp;

import com.lexinsmart.model.SingleNumberOrder;
import com.lexinsmart.service.SinglenumberOrderService;
/**
 * 排号的实例，在货运管理里面已经调用了。这里只是测试使用。
 * @author xushun
 *
 */
public class SingleNumberOrderAction {

	
	public static void main(String[] args) {
		SingleNumberOrder order = new SingleNumberOrder();
		order.setCarno("苏EE0001"); //车牌
		order.setHight(0);//车高   
		order.setSingleno("1030t11111");//  提入单号
		order.setWeightimes(1);
		order.setOrderno(0);
		order.setDepart("long");//装卸点
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());
		order.setCreatetime(nowtime);
		order.setTtype("平板货车");
		
		SinglenumberOrderService orderService = new SinglenumberOrderService();
		orderService.addOrder(order);
		orderService.release();
		
	}
}
