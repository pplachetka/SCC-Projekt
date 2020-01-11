package com.scc.speiseplan.testClient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class client {

    public static void main(String[] args) {
        try {

            Client client = Client.create();

            /* Test on sending array of jsons*/
            WebResource webResource = client
                    .resource("http://localhost:8080/speiseplan/endpoint/schedule/setMenuItemSchedule");

            String input = "{\"token\":\"1\", \"menuItemSchedule\":[{\"date\":\"20190101\",\"position\":\"1\",\"menuItemID\":\"2\"},{\"date\":\"20190101\",\"position\":\"1\",\"menuItemID\":\"1\"}] }";
//[{"date":"20190101","position":"1","menuItemID":"essen A"},{"date":"20190101","position":"2","menuItemID":"essen B"},{"date":"20190101","position":"3","menuItemID":"essen C"},{"date":"20190102","position":"","menuItemID":""}]
            ClientResponse response = webResource.type("application/json")
                    .post(ClientResponse.class, input);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            System.out.println("Output from Server .... \n");
            String output = response.getEntity(String.class);
            System.out.println(output);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}