package automation_8_query_param;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashwiseAuthorization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientQueryParam {

    /** Test Case
     *  Status code 200 OK
     *  Client ID is not Null
     *  Company name is not null
     *  Client Name is not null
     */


    @Test
    public void test_1_getAllClientsParam(){

        String url = "https://backend.cashwise.us/api/myaccount/clients";


        Map<String, Object> param = new HashMap<>();
        param.put("isArchived", false);
        param.put("page", 1);
        param.put("size", 4);

        Response response = RestAssured.given()
                .auth().oauth2(CashwiseAuthorization.getToken())
                .queryParams( param  )
                .get( url );


        // response.prettyPrint();

        // responses[0].company_name

        String clientID = response.jsonPath().getString("responses[0].client_id");
        String companyName = response.jsonPath().getString("responses[0].company_name");
        String clientName = response.jsonPath().getString("responses[0].client_name");


        Assert.assertEquals("Status code is NOT 200 OK",200, response.statusCode());



        System.out.println("Client ID is : " + clientID);
        Assert.assertNotNull("ClientID is NULL",clientID);

        System.out.println("Company name is : " + companyName);
        Assert.assertNotNull("Company name is NULL", companyName );

        System.out.println("Client name is : " + clientName);
        Assert.assertNotNull("Client name is NULL", clientName );


        System.out.println("============Assert list of clients===============================");

        List<Map<String, Object>> listOfClients =  response.jsonPath().getList("responses");

        for (int i=0; i<listOfClients.size();i++  ){

             clientID = response.jsonPath().getString("responses["  + i +  "].client_id");
             companyName = response.jsonPath().getString("responses["  +i + "].company_name");
             clientName = response.jsonPath().getString("responses[" +i+ "].client_name");

            System.out.println("Client ID is : " + clientID);
            Assert.assertNotNull("ClientID is NULL",clientID);

            System.out.println("Company name is : " + companyName);
            Assert.assertNotNull("Company name is NULL", companyName );

            System.out.println("Client name is : " + clientName);
            Assert.assertNotNull("Client name is NULL", clientName );


        }







    }



}
