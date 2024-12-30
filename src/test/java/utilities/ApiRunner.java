package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import pojo.CustomResponses;
import pojo.RequestBody;

public class ApiRunner {

    // This is getter method for CustomResponses. To be able to read value of our CustomResponses
    @Getter
    private static CustomResponses customResponses;

    @Getter
    private static int statusCode;

    @Getter
    private static CustomResponses[] customResponsesArray;

    // GET Request
    public static CustomResponses runGET(String path)  {

      String url = Config.getProperty("basURI")   + path;

        Response response = RestAssured.given()
                .auth().oauth2(  CashwiseAuthorization.getToken() )
                .get(url);

        statusCode = response.getStatusCode();

        ObjectMapper mapper = new ObjectMapper();

        try {
             customResponses = mapper.readValue(response.asString(), CustomResponses.class );
        } catch (JsonProcessingException e) {
            System.out.println("LIST response");

                    try {
                        customResponsesArray = mapper.readValue( response.asString(), CustomResponses[].class );
                    } catch (JsonProcessingException ex) {
                        System.out.println("Maybe LIST response cannot be Deserialize");
                        throw new RuntimeException(ex);

                    }

        }

        return customResponses;

    }


    // POST request
    public static CustomResponses runPOST(String path, RequestBody requestBody)  {
        String url = Config.getProperty("basURI")  +  path;

        Response response = RestAssured.given()
                .auth().oauth2( CashwiseAuthorization.getToken()  )
                .contentType( ContentType.JSON )
                .body( requestBody )
                .post(  url );

        statusCode = response.getStatusCode();

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
        } catch (JsonProcessingException e) {
            System.out.println("Maybe LIST response");
            throw new RuntimeException(e);
        }

        return customResponses;

    }


    // PUT Request
    public static CustomResponses runPUT(String path, RequestBody requestBody)  {
        String url = Config.getProperty("basURI")  +  path;

        Response response = RestAssured.given()
                .auth().oauth2( CashwiseAuthorization.getToken()  )
                .contentType( ContentType.JSON )
                .body( requestBody )
                .put(  url );

        statusCode = response.getStatusCode();

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
        } catch (JsonProcessingException e) {
            System.out.println("Maybe LIST response");
            throw new RuntimeException(e);
        }

        return customResponses;

    }


    // DELETE Request
    public static CustomResponses runDELETE(String path)  {

        String url = Config.getProperty("basURI")   + path;

        Response response = RestAssured.given()
                .auth().oauth2(  CashwiseAuthorization.getToken() )
                .delete(url);

        statusCode = response.getStatusCode();

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class );
        } catch (JsonProcessingException e) {
            System.out.println("Maybe LIST response");
            throw new RuntimeException(e);
        }
        return customResponses;
    }



}
