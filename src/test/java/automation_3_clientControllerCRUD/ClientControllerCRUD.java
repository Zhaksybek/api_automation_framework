package automation_3_clientControllerCRUD;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.runners.MethodSorters;
import utilities.CashwiseAuthorization;


import java.util.HashMap;
import java.util.Map;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientControllerCRUD {
    static String clientID;



    @Test
    public void test_1_createClient(){  // POST request
        String url ="https://backend.cashwise.us/api/myaccount/clients";

        Faker faker = new Faker();
        String companyName= faker.company().name();
        String clientName = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.phoneNumber().cellPhone();
        String address = faker.address().fullAddress();
        int[] tagId = {1150};

        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("company_name" ,   companyName );
        requestBody.put("client_name", clientName );
        requestBody.put("email", email);
        requestBody.put("phone_number",phoneNumber );
        requestBody.put("address", address);
        requestBody.put("tags_id", tagId );

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().oauth2( CashwiseAuthorization.getToken() )
                .body(requestBody)
                .post(url);


        Assert.assertEquals(201, response.statusCode());

        response.prettyPrint();

        clientID= response.jsonPath().getString("client_id");

    }



    @Test
    public void test_2_getClient(){

        String url = "https://backend.cashwise.us/api/myaccount/clients/" +clientID;
        String token = CashwiseAuthorization.getToken();

        Response response = RestAssured.given()
                .auth().oauth2(  token )
                .get( url );
        response.prettyPrint();
    }


    @Test
    public void test_3_updateClient(){
        String url ="https://backend.cashwise.us/api/myaccount/clients/" + clientID;

        Faker faker = new Faker();
        String companyName= faker.company().name() +" UPDATED";
        String clientName = faker.name().fullName() +" UPDATED";
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.phoneNumber().cellPhone();
        String address = faker.address().fullAddress();
        int[] tagId = {1150};

        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("company_name" ,   companyName );
        requestBody.put("client_name", clientName );
        requestBody.put("email", email);
        requestBody.put("phone_number",phoneNumber );
        requestBody.put("address", address);
        requestBody.put("tags_id", tagId );

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().oauth2( CashwiseAuthorization.getToken() )
                .body(requestBody)
                .put(url);

        response.prettyPrint();

    }


    @Test
    public void test_4_deleteClient() {
        String url = "https://backend.cashwise.us/api/myaccount/clients/" + clientID;

        Response response = RestAssured.given()
                .auth().oauth2( CashwiseAuthorization.getToken() )
                .delete(url);

        Assert.assertEquals(200, response.statusCode());

    }








    }
