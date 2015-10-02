package com.fauxshop.spring.swf;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.webflow.action.EventFactorySupport;
import org.springframework.webflow.execution.Event;

import java.lang.Object;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


@SuppressWarnings("serial")
public class AccountValidation implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(AccountValidation.class);

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

	public Event validateAccountValues(String userLogin,
			String email,
			String password,
			String firstName,
			String lastName,
			String city,
			String state,
			String zip,
			String phoneNumber,
			String country,
			String address,
			String address2,			
			MessageContext messageContext) {
		
		/*Validate account values:*/
		String check_userlogin = "[a-zA-Z0-9]{3,45}\\w*";
		if (!userLogin.matches(check_userlogin)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("user_login_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}

		/*email:*/
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("email_format");      
			messageContext.addMessage(errorMessageBuilder.build());		 		
		}

		/*password:*/
		String check_password = "[a-zA-Z0-9!@#$%&*]{8,99}";
		if (!password.matches(check_password)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("password_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}

		// We return all of the error messages to be displayed on the view.
		if (messageContext.hasErrorMessages()){
			return new EventFactorySupport().error(this);	
		}
		
		return new EventFactorySupport().success(this);	
	}			
}