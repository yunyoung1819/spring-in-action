package tacos.kitchen.messaging.jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import tacos.Order;
import tacos.kitchen.KitchenUI;

@Component
public class OrderListener {

	private KitchenUI ui;

	@Autowired
	public OrderListener(KitchenUI ui) {
		this.ui = ui;
	}

	@JmsListener(destination = "tacocloud.order.quque")
	public void receiveOrder(Order order) {
		ui.displayOrder(order);
	}
}
