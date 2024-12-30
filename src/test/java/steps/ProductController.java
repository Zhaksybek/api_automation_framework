package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pojo.RequestBody;
import utilities.ApiRunner;

public class ProductController {
    static String path;
    static int productID;

    @Given("the endpoint {string}")
    public void the_endpoint(String endpoint) {
        path = endpoint;
    }
    @Given("I create a new product")
    public void i_create_a_new_product() {
        Faker faker = new Faker();

        RequestBody requestBody = new RequestBody();

        requestBody.setProduct_title(  faker.commerce().productName() );
        requestBody.setProduct_price(faker.number().numberBetween(100, 2500));
        requestBody.setService_type_id(2);
        requestBody.setCategory_id(2007);
        requestBody.setProduct_description(  faker.lorem().sentence() );

        ApiRunner.runPOST( path, requestBody );

        Assert.assertEquals("Status code is not 201", 201, ApiRunner.getStatusCode());

        String productTitle = ApiRunner.getCustomResponses().getProduct_title();;

        System.out.println( "Product title before updated: " + productTitle );

        System.out.println("======Product successfully created============");

    }
    @When("I retrieve the same product by its ID")
    public void i_retrieve_the_same_product_by_its_id() {
        productID = ApiRunner.getCustomResponses().getProduct_id();

        //  /api/myaccount/products   +  "/"  +   2752
        path = path + "/" + productID;

        ApiRunner.runGET(path);

    }
    @Then("I validate the response body and ensure the status code is {long}")
    public void i_validate_the_response_body_and_ensure_the_status_code_is(long expectedStatusCode) {
        long actualStatusCode = ApiRunner.getStatusCode();
        Assert.assertEquals(   "Status code is NOT 200 OK",expectedStatusCode, actualStatusCode);
        System.out.println("status code is: " + actualStatusCode);
    }
    @When("I update the same product by its ID")
    public void i_update_the_same_product_by_its_id() {
        Faker faker = new Faker();

        RequestBody requestBody = new RequestBody();

        requestBody.setProduct_title(  faker.commerce().productName() + " UPDATED" );
        requestBody.setProduct_price(faker.number().numberBetween(100, 2500));
        requestBody.setService_type_id(2);
        requestBody.setCategory_id(2007);
        requestBody.setProduct_description(  faker.lorem().sentence() );

        //  /api/myaccount/products/{id}
        ApiRunner.runPUT(path, requestBody );

    }
    @Then("I validate the updated product details in the response")
    public void i_validate_the_updated_product_details_in_the_response() {
        String productTitle = ApiRunner.getCustomResponses().getProduct_title();

        System.out.println(  "Product title is: "+ productTitle);

        Assert.assertTrue( "Product Title is not updated",productTitle.contains("UPDATED") );

        System.out.println("========Product updated successfully================");


    }
    @When("I delete the same product by its ID")
    public void i_delete_the_same_product_by_its_id() {

        ApiRunner.runDELETE(path);
        Assert.assertEquals("Expected status code 200", 200, ApiRunner.getStatusCode());



    }
    @Then("I confirm the product no longer exists in the system")
    public void i_confirm_the_product_no_longer_exists_in_the_system() {
        // I am trying to delete same product again and expecting 500
        ApiRunner.runDELETE(path);

        Assert.assertEquals("Expected status code 500, but received a different value.", 500, ApiRunner.getStatusCode());

        System.out.println("========Product updated deleted================");

    }




}
