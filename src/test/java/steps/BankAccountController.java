package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pojo.RequestBody;
import utilities.ApiRunner;

public class BankAccountController {
    static String path;
    static String bankID;


    @Given("the API endpoint {string}")
    public void the_api_endpoint(String endpoint) {
        path = endpoint;
    }
    @When("I create a bank account with {string}, {string}, {string}, and {double}")
    public void i_create_a_bank_account_with_and(String bank_account_name, String description, String type_of_pay, double balance) {
        Faker faker = new Faker();
        String accountName = faker.company().name() + " "+bank_account_name;

        RequestBody requestBody  = new RequestBody();
        requestBody.setType_of_pay(  type_of_pay );
        requestBody.setBank_account_name(  accountName );
        requestBody.setDescription( description  );
        requestBody.setBalance( balance );

        ApiRunner.runPOST( path, requestBody );

        bankID = ApiRunner.getCustomResponses().getId();

        Assert.assertEquals("Status code is not 201", 201, ApiRunner.getStatusCode());
        System.out.println("=======Bank account created==================");

    }
    @When("I retrieve the created bank account by its ID")
    public void i_retrieve_the_created_bank_account_by_its_id() {
       // /api/myaccount/bankaccount + "/" +  {id}
        path = path + "/" + bankID;

        ApiRunner.runGET(path);

    }
    @Then("I verify the response status code is {long}")
    public void i_verify_the_response_status_code_is(long expectedStatusCode) {
        long actualStatusCode = ApiRunner.getStatusCode();

        Assert.assertEquals("Status code is not 200",expectedStatusCode, actualStatusCode);
        System.out.println("=======Bank account status code is: " + actualStatusCode);
    }
    @Then("I delete the same bank account by its ID")
    public void i_delete_the_same_bank_account_by_its_id() {
        ApiRunner.runDELETE(path);
        Assert.assertEquals("Status code is not 200",200, ApiRunner.getStatusCode());

    }
    @Then("I confirm the bank account is removed")
    public void i_confirm_the_bank_account_is_removed() {
        ApiRunner.runDELETE(path);
        Assert.assertEquals("Status code is not 500",500, ApiRunner.getStatusCode());

        System.out.println("=======================TEST PASSED!!!=======================================");

    }



}
