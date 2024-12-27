package automation_7_practice_array_pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.CustomResponses;
import utilities.CashwiseAuthorization;

public class SellerControllerArray {

    @Test
    public void test_1_getAllSellers(){

        String url = "https://backend.cashwise.us/api/myaccount/sellers/all";

        Response response = RestAssured.given()
                .auth().oauth2( CashwiseAuthorization.getToken() )
                .get(url);

        Assert.assertEquals(200, response.statusCode());
        System.out.println(" Status code is: "+ response.statusCode());

        // Return to us size of Array(Sellers)
        int sizeOfSellers = response.jsonPath().getList("").size();

        for(  int i = 0; i < sizeOfSellers; i++ ){
            String sellerID = response.jsonPath().getString("["  + i +   "].seller_id");

            System.out.println( "Seller ID is: " + sellerID );

            Assert.assertNotNull(sellerID);

            String companyName = response.jsonPath().getString("[" + i +  "].company_name");

            System.out.println("Company name is: "+ companyName);

            Assert.assertNotNull( companyName );

        }

    }

    @Test
    public void test_2_getAllSellersPOJO() throws JsonProcessingException {

        String url = "https://backend.cashwise.us/api/myaccount/sellers/all";

        Response response = RestAssured.given()
                .auth().oauth2( CashwiseAuthorization.getToken() )
                .get(url);

        Assert.assertEquals(200, response.statusCode());
        System.out.println(" Status code is: "+ response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        CustomResponses[] customResponses = mapper.readValue( response.asString(), CustomResponses[].class );

        // System.out.println(  customResponses[0].getSeller_id() );


        for (  int i=0;  i< customResponses.length; i++  ){
            int sellerID = customResponses[i].getSeller_id();

            System.out.println( "Seller ID is: " + sellerID );
            Assert.assertNotNull( sellerID );

            String sellerName = customResponses[i].getSeller_name();
            System.out.println(  "Seller Name is: " + sellerName );
            Assert.assertNotNull(sellerName);

        }




    }




}
