package com.fauxshop.spring.swf;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.action.EventFactorySupport;
import org.springframework.webflow.execution.Event;

import java.lang.Object;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


@SuppressWarnings("serial")
public class Validate implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(Validate.class);
	
	// This method appears to be bypassed by spring security. Not certain how to validate login credentials yet. It's not particularly necessary.
	public Event validateLoginForm(String userLogin, String password, MessageContext messageContext) {
		
		if (null == userLogin || null == password) {
            MessageBuilder errorMessageBuilder = new MessageBuilder().error();
            errorMessageBuilder.source("username");
            errorMessageBuilder.code("username_blank");      
            messageContext.addMessage(errorMessageBuilder.build());
            return new EventFactorySupport().error(this);			
		}
		
		return new EventFactorySupport().success(this);
	}	
	
	public Event validateRegistrationEmail(String email, MessageContext messageContext) {

		/*Validate email format:*/
		 try {
			InternetAddress emailAddr = new InternetAddress(email);
				emailAddr.validate();
		 	} catch (AddressException ex) {
	            MessageBuilder errorMessageBuilder = new MessageBuilder().error();
	            errorMessageBuilder.source("email");
	            errorMessageBuilder.code("email_format");      
	            messageContext.addMessage(errorMessageBuilder.build());		 		
		 		return new EventFactorySupport().error(this);	
		 	}
		
		return new EventFactorySupport().success(this);
	}		
}