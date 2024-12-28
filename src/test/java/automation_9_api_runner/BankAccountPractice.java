package automation_9_api_runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.CustomResponses;
import utilities.ApiRunner;
import utilities.CashwiseAuthorization;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankAccountPractice {

    @Test
    public void test_1_getBank(){ // Option 1
        String url= "https://backend.cashwise.us/api/myaccount/bankaccount/1854";

        Response response = RestAssured.given()
                            .auth().oauth2(CashwiseAuthorization.getToken())
                            .get(url);

        response.prettyPrint();

        // jsonPath()
        String bankID = response.jsonPath().getString("id");
        String bankAccountName = response.jsonPath().getString("bank_account_name");


        System.out.println(bankID);
        System.out.println(bankAccountName);

    }




    @Test
    public void test_2_getBank() throws JsonProcessingException { // Option 2
        String url= "https://backend.cashwise.us/api/myaccount/bankaccount/1854";

        Response response = RestAssured.given()
                .auth().oauth2(CashwiseAuthorization.getToken())
                .get(url);
        ObjectMapper mapper = new ObjectMapper();
        CustomResponses customResponses = mapper.readValue(response.asString(), CustomResponses.class);

        System.out.println( "Bank ID: " +  customResponses.getId()  );

    }


    @Test
    public void test_3_getBankByAPIRunner(){
        String path =  "/api/myaccount/bankaccount/1854";
        String bankID = ApiRunner.runGET(path).getId();
        System.out.println("Bank ID is: " + bankID);

        String bankAccountName = ApiRunner.runGET(path).getBank_account_name();
        System.out.println("Bank Account name: " + bankAccountName);
    }


    @Test
    public void test_4_getOneSeller(){
        String path = "/api/myaccount/sellers/6087";
        int sellerID = ApiRunner.runGET(path).getSeller_id();

        System.out.println("Seller ID: "+ sellerID);

        String sellerName = ApiRunner.runGET(path).getSeller_name();

        System.out.println("Seller name: " + sellerName);

        Assert.assertNotNull(sellerName);


    }





}
