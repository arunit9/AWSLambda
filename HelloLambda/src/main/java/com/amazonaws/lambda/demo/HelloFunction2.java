package com.amazonaws.lambda.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

public class HelloFunction2 implements RequestStreamHandler {

	private static final String DYNAMODB_TABLE_NAME = "Rides"; 
    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

    	JSONParser parser = new JSONParser();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    	JSONObject responseJson = new JSONObject();
    	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDB dynamoDb = new DynamoDB(client);
    	try {
            JSONObject event = (JSONObject) parser.parse(reader);
            JSONObject responseBody = new JSONObject();
     
            if (event.get("queryStringParameters") != null) {
            	JSONObject pps = (JSONObject) event.get("queryStringParameters");
                if (pps.get("id") != null) {
	                int id = Integer.parseInt((String) pps.get("id"));
	            	responseBody.put("message", id);
	            	dynamoDb.getTable(DYNAMODB_TABLE_NAME)
	                			.putItem(new PutItemSpec().withItem(new Item().withString("RideId", ""+id)
	                			.withString("name", "person" + id)));
                }
                else {
                	responseBody.put("message", 0);
                }
//                Person person = new Person((String) event.get("body"));
//     
//                dynamoDb.getTable(DYNAMODB_TABLE_NAME)
//                  .putItem(new PutItemSpec().withItem(new Item().withNumber("id", person.getId())
//                    .withString("name", person.getName())));
            }
     
//            JSONObject responseBody = new JSONObject();
//            responseBody.put("message", "New item created");
//     
//            JSONObject headerJson = new JSONObject();
//            headerJson.put("x-custom-header", "my custom header value");
     
            responseJson.put("statusCode", 200);
//            responseJson.put("headers", headerJson);
            responseJson.put("body", responseBody.toString());
     
        } catch (ParseException pex) {
            responseJson.put("statusCode", 400);
            responseJson.put("exception", pex);
        }
    	OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
        writer.write(responseJson.toString());
        writer.close();
    }

}
