package automation_11_array_api_runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.CustomResponses;
import utilities.ApiRunner;
import utilities.CashwiseAuthorization;

public class SellerArray {

    @Test
    public void test_1_getAllSellersPOJO() throws JsonProcessingException {

        String url = "https://backend.cashwise.us/api/myaccount/sellers/all";

        Response response = RestAssured.given()
                .auth().oauth2( CashwiseAuthorization.getToken() )
                .get(url);

        Assert.assertEquals(200, response.statusCode());
        System.out.println(" Status code is: "+ response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        CustomResponses[] customResponses = mapper.readValue( response.asString(), CustomResponses[].class );

        // System.out.println(  customResponses[0].getSeller_id() );


        for (  int i=0;  i < customResponses.length; i++  ){
            int sellerID = customResponses[i].getSeller_id();

            System.out.println( "Seller ID is: " + sellerID );
            Assert.assertNotNull( sellerID );

            String sellerName = customResponses[i].getSeller_name();
            System.out.println(  "Seller Name is: " + sellerName );
            Assert.assertNotNull(sellerName);

        }


    }


    @Test
    public void test_2_getAllSellersAPIRunner(){

        String path = "/api/myaccount/sellers/all";

        ApiRunner.runGET(path);

        int statusCode =  ApiRunner.getStatusCode();

        System.out.println( "Status code: "  + statusCode );

        int sellerId = ApiRunner.getCustomResponsesArray()[0].getSeller_id();

        CustomResponses[] customResponses = ApiRunner.getCustomResponsesArray();

        System.out.println( "SellerID: "+ sellerId );


        for ( int  i=0;  i<customResponses.length; i++ ){

            sellerId =  ApiRunner.getCustomResponsesArray()[i].getSeller_id();

            System.out.println( "SellerID: " +  sellerId  );

            Assert.assertNotNull( sellerId   );

        }

    }




}
