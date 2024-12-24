package automation_2_cashwiseAuth;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthCashwise {

    static String token;
    static String bankID;


    @Test
    public void test_1_tokenGenerator(){

        String url = "https://backend.cashwise.us/api/myaccount/auth/login";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", "codewisenew@gmail.com");
        requestBody.put("password","123456" );

        Response response =
                RestAssured.given()
                .contentType(ContentType.JSON)
                        .body(requestBody)
                        .post(url);

        System.out.println("Status code is: "+ response.statusCode());
        Assert.assertEquals(200, response.statusCode());

        // Print response body in pretty format
        // response.prettyPrint();

        token = response.jsonPath().getString("jwt_token");

        System.out.println(token);

        System.out.println("=======Token successfully generated=======================");


    }


    @Test
    public void test_2_getAllBankAccounts(){
        String url = "https://backend.cashwise.us/api/myaccount/bankaccount";

        Response response =
                RestAssured.given()
                        .auth().oauth2( token  )
                        .get(url);

        System.out.println(response.statusCode());
        response.prettyPrint();

    }

    @Test
    public void test_3_createBankAccount(){

        String url = "https://backend.cashwise.us/api/myaccount/bankaccount";

        Faker faker = new Faker();
        String bankName = faker.company().name() + " Bank";

        Map<String , Object> requestBody = new HashMap<>();
        requestBody.put("type_of_pay", "CASH");
        requestBody.put("bank_account_name", bankName);
        requestBody.put("description","Financial comapany");
        requestBody.put("balance", 1000);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().oauth2( token )
                .body(requestBody)
                .post(url);

        Assert.assertEquals(201, response.statusCode());

        bankID = response.jsonPath().getString("id");
        response.prettyPrint();

    }


    @Test
    public void test_4_getBankAccount(){

        String url = "https://backend.cashwise.us/api/myaccount/bankaccount/"+bankID;

        Response response = RestAssured.given()
                .auth().oauth2( token )
                .get(url);

        response.prettyPrint();

        Assert.assertEquals(200, response.statusCode());


    }

    @Test
    public void test_5_updateBankAccount(){
        String url ="https://backend.cashwise.us/api/myaccount/bankaccount/"+ bankID;

        Faker faker = new Faker();
        String bankName = faker.company().name() + " Bank UPDATED";

        Map<String , Object> requestBody = new HashMap<>();
        requestBody.put("type_of_pay", "CASH");
        requestBody.put("bank_account_name", bankName);
        requestBody.put("description","Financial comapany");
        requestBody.put("balance", 1000);


        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body( requestBody )
                .put(  url );

        Assert.assertEquals(200, response.statusCode());

        response.prettyPrint();



    }

    // https://backend.cashwise.us/api/myaccount/bankaccount/1849


    @Test
    public void test_6_deleteBankAccount(){
        String url = "https://backend.cashwise.us/api/myaccount/bankaccount/"+ bankID;

        Response response = RestAssured.given()
                .auth().oauth2( token )
                .delete( url );


        Assert.assertEquals(200, response.statusCode() );


        System.out.println("=======BANK ACCOUNT DELETED=============================");
        System.out.println("======= Test PASSED! =============================");
    }



}
