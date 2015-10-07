package com.fauxshop.spring.swf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;

@SuppressWarnings("serial")
public class Stripe implements Serializable {

    public static void processStripePayment(Integer amount, String stripeToken) {
        RequestOptions requestOptions = (new RequestOptions.RequestOptionsBuilder()).setApiKey("sk_test_pJRywYDR2jqqKi9nn7qQ5ADy").build();
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", amount);
        chargeMap.put("currency", "usd");
        Map<String, Object> cardMap = new HashMap<String, Object>();
        chargeMap.put("source", stripeToken);
        chargeMap.put("description", "Charge from Fauxshop");
        chargeMap.put("card", cardMap);               
        try {
            Charge charge = Charge.create(chargeMap, requestOptions);
            System.out.println(charge);
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}