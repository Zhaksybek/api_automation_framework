package automation_6_crud_pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.CustomResponses;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SellerCRUDPojo {

    /** DO CRUD commands for Seller controller
     *  CREATE Seller
     * Read Seller
     * Update Seller
     * Delete Seller
     */

    static int sellerID;
    static String companyName;
    static String email;


    @Test
    public void test_1_createNewSeller() throws JsonProcessingException {

        String url ="https://backend.cashwise.us/api/myaccount/sellers";

        Faker faker = new Faker();

        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name(  faker.company().name() );
        requestBody.setSeller_name(   faker.name().fullName() );
        requestBody.setEmail( faker.internet().emailAddress()  );
        requestBody.setPhone_number(  faker.phoneNumber().cellPhone() );
        requestBody.setAddress(  faker.address().fullAddress() );


        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().oauth2(  CashwiseAuthorization.getToken() )
                .body( requestBody )
                .post(  url );

        // response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponses customResponses = mapper.readValue(response.asString(),  CustomResponses.class);

        sellerID = customResponses.getSeller_id();
        companyName = customResponses.getCompany_name();
        email = customResponses.getEmail();



        System.out.println("Seller ID: " + sellerID);
        System.out.println("Company name: " + companyName);
        System.out.println("Email: " + email);



    }


    @Test
    public void test_2_readSeller() throws JsonProcessingException {

        String url = "https://backend.cashwise.us/api/myaccount/sellers/" + sellerID;

        Response response = RestAssured.given()
                .auth().oauth2(  CashwiseAuthorization.getToken() )
                .get(  url );


        ObjectMapper mapper = new ObjectMapper();
        CustomResponses customResponses = mapper.readValue( response.asString(), CustomResponses.class );

        Assert.assertEquals( "Company name is NOT matching", companyName, customResponses.getCompany_name() );
        Assert.assertEquals("Email is Not matching", email ,  customResponses.getEmail());



        System.out.println("Test PASSED!");

    }


    @Test
    public void test_3_updateSeller() throws JsonProcessingException {

        String url = "https://backend.cashwise.us/api/myaccount/sellers/" + sellerID;


        Faker faker = new Faker();

        RequestBody requestBody = new RequestBody();

        requestBody.setCompany_name(  faker.company().name() + " Updated" );

        requestBody.setSeller_name(   faker.name().fullName() );
        requestBody.setEmail( faker.internet().emailAddress()  );
        requestBody.setPhone_number(  faker.phoneNumber().cellPhone() );
        requestBody.setAddress(  faker.address().fullAddress() );


        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().oauth2(  CashwiseAuthorization.getToken() )
                .body( requestBody )
                .put(  url );

        ObjectMapper mapper = new ObjectMapper();

        CustomResponses customResponses = mapper.readValue(response.asString(), CustomResponses.class);



        Assert.assertNotEquals("Company name is matching", companyName ,  customResponses.getCompany_name() );
        Assert.assertNotEquals( "Email is matching", email ,   customResponses.getEmail() );


        System.out.println( "My previous company name is: "+companyName );
        System.out.println("My updated company name is: " + customResponses.getCompany_name() );


        System.out.println("Previous email is: " + email);
        System.out.println("Updated email is: " + customResponses.getEmail() );




    }



    @Test
    public void test_4_deleteSeller() throws JsonProcessingException {

        String url = "https://backend.cashwise.us/api/myaccount/sellers/" + sellerID;

        Response response = RestAssured.given()
                .auth().oauth2(  CashwiseAuthorization.getToken() )
                .delete(  url );


        Assert.assertEquals(200,  response.statusCode());



    }






}
