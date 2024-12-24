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



    }


}
