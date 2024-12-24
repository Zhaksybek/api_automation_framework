package automation_1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class ReqresinCRUD {

    @Test
    public void test_1_getSingleUser(){

        String url ="https://reqres.in/api/users/2";

        Response response = RestAssured.get( url );

        response.prettyPrint();

        System.out.println("Status code is: " +   response.statusCode() );


        Assert.assertEquals(200, response.statusCode() );




    }





}
