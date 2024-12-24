package automation_1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

public class ReqresIn {


    public static void main(String[] args) {

        // URL to able call get request
        String url = "https://reqres.in/api/users/2";
        // RestAssured lybraries  we are able to call GET request
        Response response = RestAssured.get( url );

        // prettyPrint to able to get pretty response
        response.prettyPrint();

        // Step - 1 we have to print json object
        System.out.println("======Print json object========================");
        response.prettyPrint();

        // Step - 2 we have to find json path of our target

        // Step - 3 we have to use response.jsonPath().getString("data.first_name")

        String firstName = response.jsonPath().getString("data.first_name");

        System.out.println(  "Expected first name is: "+firstName  );

        String lastName = response.jsonPath().getString("data.last_name");

        System.out.println("Expected last name is: " + lastName);

       // ==================================
        // data.email

        String email = response.jsonPath().getString("data.email");
        System.out.println("Expected email is: "+ email);




        // Validate Response body, use Json path. Validate first name is Janet
        //$.data.first_name


    }


}
