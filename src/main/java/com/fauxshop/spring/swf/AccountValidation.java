package com.fauxshop.spring.swf;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.webflow.action.EventFactorySupport;
import org.springframework.webflow.execution.Event;

import com.fauxshop.spring.service.AccountService;

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
			String shipName,
			String shipCity,
			String shipState,
			String shipZip,
			String shipPhone,
			String shipCountry,
			String shipAddress,
			String shipAddress2,	
			boolean isUserLoginAlreadyRegistered,
			MessageContext messageContext) {
		
		/*Validate account values:*/
		String check_userlogin = "[a-zA-Z0-9]{3,45}\\w*";
		if (!userLogin.matches(check_userlogin)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("user_login_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}
		if (!isUserLoginAlreadyRegistered) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("user_login_already_exists");      
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
		String check_password = "[a-zA-Z0-9!@#$%&*\\s-]{8,99}";
		if (!password.matches(check_password)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("password_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}
		
		/*firstName:*/
		String check_firstName = "^[a-zA-Z\\s]{1,99}";
		if (!firstName.matches(check_firstName)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("firstName_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}	
		
		/*lastName:*/
		String check_lastName = "^[a-zA-Z\\s]{1,99}";
		if (!lastName.matches(check_lastName)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("lastName_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}
		
		/*city:*/
		String check_city = "^[a-zA-Z-\\s]{1,99}";
		if (!city.matches(check_city)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("city_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}	
		
		/*state:*/
		String check_state = "[a-zA-Z]{2}$";
		if (!state.matches(check_state)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("state_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}	
		
		/*state:*/
		String check_zip = "[0-9-]{5,11}";
		if (!zip.matches(check_zip)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("zip_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}			
	    
		/*phoneNumber:*/
	    //validate phone numbers of format "1234567890"
	    if (phoneNumber.matches("\\d{10}")){
	    //validating phone number with -, . or spaces
	    } else if (phoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
	    //validating phone number with extension length from 3 to 5
	    } else if (phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
	    //validating phone number where area code is in braces ()
	    } else if (phoneNumber.matches("\\(\\d{3}\\)-\\s\\d{3}-\\d{4}")) {
	    //return false if nothing matches the input
	    } else {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("phoneNumber_error");      
			messageContext.addMessage(errorMessageBuilder.build());		
	    }
		
		/*country:*/
		String check_country = "^[a-zA-Z\\s]*${2,99}";
		if (!country.matches(check_country)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("country_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}		
		
		/*address:*/
		String check_address = "[a-zA-Z0-9.!@#$%&*\\s-]{1,99}";
		if (!address.matches(check_address)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("address_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}	
		
		/*address2:*/
		String check_address2 = "[a-zA-Z0-9.!@#$%&*\\s-]{0,99}";
		if (!address2.matches(check_address2)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("address2_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}						

		// We return all of the error messages to be displayed on the view.
		if (messageContext.hasErrorMessages()){
			return new EventFactorySupport().error(this);	
		}
		
		return new EventFactorySupport().success(this);	
	}			
	
	public Event validateSessionAccountValues(String email,		
			String shipName,
			String shipCity,
			String shipState,
			String shipZip,
			String shipPhone,
			String shipCountry,
			String shipAddress,
			String shipAddress2,	
			MessageContext messageContext) {

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
		
		/*firstName:*/
		String check_shipName = "^[a-zA-Z\\s]{1,99}";
		if (!shipName.matches(check_shipName)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("shipName_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}	
		
		/*city:*/
		String check_city = "^[a-zA-Z-\\s]{1,99}";
		if (!shipCity.matches(check_city)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("city_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}	
		
		/*state:*/
		String check_state = "[a-zA-Z]{2}$";
		if (!shipState.matches(check_state)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("state_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}	
		
		/*state:*/
		String check_zip = "[0-9-]{5,11}";
		if (!shipZip.matches(check_zip)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("zip_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}			
	    
		/*phoneNumber:*/
	    //validate phone numbers of format "1234567890"
	    if (shipPhone.matches("\\d{10}")){
	    //validating phone number with -, . or spaces
	    } else if (shipPhone.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
	    //validating phone number with extension length from 3 to 5
	    } else if (shipPhone.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
	    //validating phone number where area code is in braces ()
	    } else if (shipPhone.matches("\\(\\d{3}\\)-\\s\\d{3}-\\d{4}")) {
	    //return false if nothing matches the input
	    } else {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("phoneNumber_error");      
			messageContext.addMessage(errorMessageBuilder.build());		
	    }
		
		/*country:*/
		String check_country = "^[a-zA-Z\\s]*${2,99}";
		if (!shipCountry.matches(check_country)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("country_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}		
		
		/*address:*/
		String check_address = "[a-zA-Z0-9.!@#$%&*\\s-]{1,99}";
		if (!shipAddress.matches(check_address)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("address_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}	
		
		/*address2:*/
		String check_address2 = "[a-zA-Z0-9.!@#$%&*\\s-]{0,99}";
		if (!shipAddress2.matches(check_address2)) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("account");
			errorMessageBuilder.code("address2_error");      
			messageContext.addMessage(errorMessageBuilder.build());		 			
		}						

		// We return all of the error messages to be displayed on the view.
		if (messageContext.hasErrorMessages()){
			return new EventFactorySupport().error(this);	
		}
		
		return new EventFactorySupport().success(this);	
	}			
	
}