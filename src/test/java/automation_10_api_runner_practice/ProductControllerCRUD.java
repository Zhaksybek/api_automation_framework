package automation_10_api_runner_practice;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.RequestBody;
import utilities.ApiRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerCRUD { // ApiRunner

    static int productID;

    @Test
    public void test_1_createNewProduct(){  // Create
        String path = "/api/myaccount/products";
        Faker faker = new Faker();

        RequestBody requestBody = new RequestBody();

        requestBody.setProduct_title(  faker.commerce().productName() );
        requestBody.setProduct_price(faker.number().numberBetween(100, 2500));
        requestBody.setService_type_id(2);
        requestBody.setCategory_id(2007);
        requestBody.setProduct_description(  faker.lorem().sentence() );

        ApiRunner.runPOST(path, requestBody );
        productID = ApiRunner.getCustomResponses().getProduct_id();

        System.out.println("Product ID is: "+ productID);
        Assert.assertNotNull(productID);

        String productTitle = ApiRunner.getCustomResponses().getProduct_title();
        System.out.println( "Product title: "  + productTitle );

        Assert.assertEquals(201, ApiRunner.getStatusCode() );
        System.out.println( "Status code is: " +  ApiRunner.getStatusCode()  );

    }

    @Test
    public void test_2_readNewProduct(){ // Read new product
        String path = "/api/myaccount/products/"  + productID ;
        ApiRunner.runGET(path);
        String productTitle =  ApiRunner.getCustomResponses().getProduct_title();

        System.out.println(  "Product Title is: " + productTitle );
        Assert.assertNotNull(   productTitle );

        Assert.assertEquals(200, ApiRunner.getStatusCode() );
        System.out.println( "Status code is: " +  ApiRunner.getStatusCode()  );

    }

    @Test
    public void test_3_updateNewProduct() { // Update new product
        String path = "/api/myaccount/products/" + productID;

        Faker faker = new Faker();

        RequestBody requestBody = new RequestBody();

        requestBody.setProduct_title(  faker.commerce().productName() + " UPDATED");
        requestBody.setProduct_price(faker.number().numberBetween(100, 2500));
        requestBody.setService_type_id(2);
        requestBody.setCategory_id(2007);
        requestBody.setProduct_description(  faker.lorem().sentence() );


        ApiRunner.runPUT(path,  requestBody);

        String productTitle =  ApiRunner.getCustomResponses().getProduct_title();

        System.out.println(  "Product Title is: " + productTitle );
        Assert.assertNotNull(   productTitle );

        Assert.assertEquals(200, ApiRunner.getStatusCode() );
        System.out.println( "Status code is: " +  ApiRunner.getStatusCode()  );

    }

    @Test
    public void test_4_deleteNewProduct() {
        String path = "/api/myaccount/products/" + productID;

        ApiRunner.runDELETE(path);


        String productTitle =  ApiRunner.getCustomResponses().getProduct_title();

        System.out.println(  "Product Title is: " + productTitle );
        Assert.assertNotNull(   productTitle );

        Assert.assertEquals(200, ApiRunner.getStatusCode() );
        System.out.println( "Status code is: " +  ApiRunner.getStatusCode()  );


    }








}
