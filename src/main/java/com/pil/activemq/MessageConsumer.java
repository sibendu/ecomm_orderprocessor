package com.pil.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import javax.jms.*;

@Component
@EnableJms
public class MessageConsumer {

	@Autowired
    private Queue input_queue;
	
	@Autowired
    private Queue output_queue;

    @Autowired
    private JmsTemplate jmsTemplate;
    
    private final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @JmsListener(destination = "${INPUT_QUEUE}")
    public void listenerPRD(TextMessage order)  throws Exception{
        logger.info("Message received {} ", order);
        
		String erpName = "PILERP"; 
		if(System.getenv("ERP_NAME") != null) {
			erpName = System.getenv("ERP_NAME");
    	}
		
		int delay = 1000;
		if(System.getenv("DELAY") != null) {
			delay = Integer.parseInt(System.getenv("DELAY"));
    	}
		System.out.println("Pausing execution for "+delay+" ms");
		Thread.sleep(delay);
		System.out.println("Start processing");
		
		java.util.Random r = new java.util.Random();
		int result = r.nextInt(100000);
		
        final String response = erpName+" Order# = "+erpName+"-"+ result + " processed; Input order was: " + order.getText();
        
        jmsTemplate.send(output_queue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
              return session.createTextMessage(response);
            }
        });
    }
    
}
