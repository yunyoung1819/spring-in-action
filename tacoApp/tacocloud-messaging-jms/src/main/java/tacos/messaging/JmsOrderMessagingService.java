package tacos.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;
import tacos.Order;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * send()를 사용해서 주문 데이터 전송하기
 */
@Service
public class JmsOrderMessagingService implements OrderMessagingService {
	private JmsTemplate jms;

	@Autowired
	public JmsOrderMessagingService(JmsTemplate jms) {
		this.jms = jms;
	}

//	@Override
//	public void sendOrder(Order order) {
//		jms.send(session -> session.createObjectMessage(order));
//	}

	@Override
	public void sendOrder(Order order) {
		jms.convertAndSend("tacocloud.order.queue", order, new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws JMSException {
				message.setStringProperty("X_ORDER_SOURCE", "WEB");
				return message;
			}
		});
	}
}
