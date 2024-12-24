package automation_1;

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
public class ReqresinCRUD {

    @Test
    public void test_1_getSingleUser(){

        String url ="https://reqres.in/api/users/2";

        Response response = RestAssured.get( url );

        response.prettyPrint();

        System.out.println("Status code is: " +   response.statusCode() );


        Assert.assertEquals(200, response.statusCode() );

    }


    @Test
    public void test_2_createUser(){
        String url = "https://reqres.in/api/users";

        // Style for request body => we can use string
        String requestBody = "{\n" +
                "    \"name\": \"Jason Born\",\n" +
                "    \"job\": \"QA Software Tester\"\n" +
                "}";

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .body( requestBody )
                .post( url );

        Assert.assertEquals(201, response.statusCode() );

        System.out.println("=======Test Passed!===========");

    }

    @Test
    public void test_3_createUserMap(){
        String url = "https://reqres.in/api/users";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Mary Katy");
        requestBody.put("job", "Product Manager");

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .body( requestBody )
                .post( url );

        Assert.assertEquals(201, response.statusCode() );

        System.out.println("=======Test Passed!===========");

    }

    @Test
    public void test_4_updateUser(){

        String url ="https://reqres.in/api/users/2";

        Map<String ,Object> requestBody = new HashMap<>();
        requestBody.put("name", "Jennifer");
        requestBody.put("job", "Scrum Master");

        Response response = RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .put( url );

        Assert.assertEquals(200, response.statusCode() );

        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");

        System.out.println("Name is : "+ name);
        System.out.println("Job is: "+ job);

        Assert.assertEquals("Jennifer", name );
        Assert.assertEquals("Scrum Master", job );

    }


    @Test
    public void test_5_deleteUser(){
        String url = "https://reqres.in/api/users/2";

        Response response = RestAssured.delete(url);

        Assert.assertEquals(204, response.statusCode()  );


    }









}
