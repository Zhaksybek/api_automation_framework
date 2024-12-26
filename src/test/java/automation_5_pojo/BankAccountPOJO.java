package automation_5_pojo;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;

import java.util.HashMap;
import java.util.Map;

public class BankAccountPOJO {

    @Test // Junit annotation
    public void test_1_createNewBankAccount(){  // Option Number ONE => paste json body directly
        String url ="https://backend.cashwise.us/api/myaccount/bankaccount";
        // to be able to create new Bank name
        Faker faker = new Faker();
        String bankName = faker.company().name()+ " BANK";

        String requestBody = "{\n" +
                "  \"type_of_pay\": \"CASH\",\n" +
                "  \"bank_account_name\": \""+bankName+"+\",\n" +
                "  \"description\": \"Financial comapany\",\n" +
                "  \"balance\": 50\n" +
                "}";

        Response response = RestAssured.given()
                .auth().oauth2( CashwiseAuthorization.getToken() )
                .contentType( ContentType.JSON )
                .body(  requestBody  )
                .post(  url  );

        System.out.println( "Status code: " +   response.statusCode());

        response.prettyPrint();

        Assert.assertEquals(201, response.statusCode());


    }


    @Test
    public void test_2_createNewBankAccount(){  // Option Number Two => Use Map for requestBody

        String url ="https://backend.cashwise.us/api/myaccount/bankaccount";

        Faker faker = new Faker();
        String bankName = faker.company().name()+ " BANK";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("type_of_pay", "BANK" );
        requestBody.put("bank_account_name", bankName);
        requestBody.put("description", "Financial company");
        requestBody.put("balance", 905 );

        Response response = RestAssured.given()
                .auth().oauth2( CashwiseAuthorization.getToken())
                .contentType( ContentType.JSON )
                .body(  requestBody )
                .post( url );

        System.out.println(response.statusCode());

        Assert.assertEquals(201, response.statusCode());

    }


    @Test
    public void test_3_createNewBankPOJO(){

        String url = "https://backend.cashwise.us/api/myaccount/bankaccount";

        Faker faker = new Faker();
        String bankName = faker.company().name()+ " BANK";


        // Serialization => Java => JSON
        RequestBody requestBody = new RequestBody();
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name( bankName );
        requestBody.setDescription("Financial company");
        requestBody.setBalance(4000);


        Response response = RestAssured.given()
                .auth().oauth2(  CashwiseAuthorization.getToken() )
                .contentType(ContentType.JSON)
                .body( requestBody  )
                .post( url );


        System.out.println(  "Status code is: "+ response.statusCode() );

        Assert.assertEquals( 201, response.statusCode() );

        System.out.println( "========My request body==========================" );

        response.prettyPrint();


    }



}
