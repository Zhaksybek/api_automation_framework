package automation_5_pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pojo.CustomResponses;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;

public class BankAccountObjectMapper {


    @Test
    public void test_1_createNewBankPOJO() throws JsonProcessingException {

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

        System.out.println( "=======Use Json Path to validate response body=========================" );

        String bankAccountName = response.jsonPath().getString("bank_account_name");
        String description = response.jsonPath().getString("description");
        double balance = response.jsonPath().getDouble("balance");


        Assert.assertNotNull(bankAccountName);
        Assert.assertNotNull(description);
        Assert.assertNotNull(balance);

        System.out.println("Bank account name is: "+bankAccountName);
        System.out.println("Description is: "+description);
        System.out.println("My balance is: "+balance);


        System.out.println( "=======Use CustomResponse to validate response body=========================" );

        System.out.println("===========Object Mapper=========================================================");

        ObjectMapper mapper = new ObjectMapper();
        CustomResponses customResponses = mapper.readValue(response.asString(), CustomResponses.class);

        String bankAccountNameByObjectMapper =  customResponses.getBank_account_name();
        String descriptionByObjectMapper = customResponses.getDescription();
        double balanceByObjectMapper = customResponses.getBalance();

        System.out.println("Bank account name is(ObjectMapper): "+bankAccountNameByObjectMapper);
        System.out.println("Description is(ObjectMapper): "+descriptionByObjectMapper);
        System.out.println("My balance is(ObjectMapper): "+balanceByObjectMapper);

    }




}
