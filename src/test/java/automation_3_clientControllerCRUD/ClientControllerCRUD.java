package automation_3_clientControllerCRUD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashwiseAuthorization;

public class ClientControllerCRUD {

    @Test
    public void test_1_createClient(){
        String url = "https://backend.cashwise.us/api/myaccount/clients/6060";
        String token = CashwiseAuthorization.getToken();

        Response response = RestAssured.given()
                .auth().oauth2(  token )
                .get( url );
        response.prettyPrint();
    }







}
