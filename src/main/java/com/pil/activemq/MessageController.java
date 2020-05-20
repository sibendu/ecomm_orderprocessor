package com.pil.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.jms.core.MessageCreator;
import javax.jms.*;

@RestController
@RequestMapping("/api")
public class MessageController {

	@Autowired
	private Queue input_queue;
	 
    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/{message}")
    public ResponseEntity<String> publish(@PathVariable("message") final String message){
    	System.out.println("Posting message to : "+input_queue);
    	Order order = new Order(message, null,null,null,"Test order");
        
    	//jmsTemplate.convertAndSend(queue, order);
        
    	jmsTemplate.send(input_queue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
              return session.createTextMessage(order.toString());
            }
        });
    	
        return new ResponseEntity(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> getMessage(){
    	System.out.println("Here..");
        return new ResponseEntity("Hello", HttpStatus.OK);
    }
}
