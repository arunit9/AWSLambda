package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Hello implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
    	//Event event = (Event)input;
        context.getLogger().log("Input: " + input);

        // TODO: implement your handler
        return "Hello "+ input +"!";
    }

}
