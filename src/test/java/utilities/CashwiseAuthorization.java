package utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CashwiseAuthorization {


    public static String token;


    public static String getToken(){

        String url = "https://backend.cashwise.us/api/myaccount/auth/login";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", "codewisenew@gmail.com");
        requestBody.put("password","123456" );

        Response response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .post(url);

        token = response.jsonPath().getString("jwt_token");
        return token;


    }


}
