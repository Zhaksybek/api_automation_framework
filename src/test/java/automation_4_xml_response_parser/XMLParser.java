package automation_4_xml_response_parser;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class XMLParser {

    static String petID;


        /**
         * TASK: CRUD operations for PetStore
         * - Create a Pet
         * - Read a Pet by ID
         * - Assert the Pet's name
         * - Assert the status code
         * - Update the Pet's name
         * - Delete the Pet
         */


    @Test
    public void test_1_createPet(){

            String url ="https://petstore.swagger.io/v2/pet";

            String requestBody ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<Pet>\n" +
                    "\t<id>0</id>\n" +
                    "\t<Category>\n" +
                    "\t\t<id>0</id>\n" +
                    "\t\t<name>dog</name>\n" +
                    "\t</Category>\n" +
                    "\t<name>Hunter</name>\n" +
                    "\t<photoUrls>\n" +
                    "\t\t<photoUrl>string</photoUrl>\n" +
                    "\t</photoUrls>\n" +
                    "\t<tags>\n" +
                    "\t\t<Tag>\n" +
                    "\t\t\t<id>0</id>\n" +
                    "\t\t\t<name>string</name>\n" +
                    "\t\t</Tag>\n" +
                    "\t</tags>\n" +
                    "\t<status>available</status>\n" +
                    "</Pet>";


            Response response = RestAssured.given()
                    .contentType(ContentType.XML) // Specify the content type of the request
                    .accept(ContentType.XML)      // Specify that the response should be in XML
                    .body( requestBody )
                    .post( url );

            // prints for us response body
            // response.prettyPrint();

            String petName = response.xmlPath().getString(  "Pet.name");
            petID = response.xmlPath().getString("Pet.id");



            System.out.println( "Pet Name is: " + petName );
            System.out.println( "Pet ID is: " + petID );

            Assert.assertEquals(200, response.statusCode());
            Assert.assertNotNull(petName);
            Assert.assertNotNull(petName);


        }

        // https://petstore.swagger.io/v2/pet/9223372036854775807

    @Test
    public void test_2_getOnePetByID(){
        String url = "https://petstore.swagger.io/v2/pet/"  + petID ;

        Response response = RestAssured.given()
                .accept(ContentType.XML)
                .get(url);

        response.prettyPrint();

        String currentName = response.xmlPath().getString("Pet.name");
        Assert.assertEquals("Hunter", currentName);
        Assert.assertEquals(200, response.statusCode());

    }


    @Test
    public void test_3_updateOnePetByID(){
        String url = "https://petstore.swagger.io/v2/pet";

        String requestBody ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Pet>\n" +
                "\t<id>0</id>\n" +
                "\t<Category>\n" +
                "\t\t<id>0</id>\n" +
                "\t\t<name>dog</name>\n" +
                "\t</Category>\n" +
                "\t<name>Ranger</name>\n" +
                "\t<photoUrls>\n" +
                "\t\t<photoUrl>string</photoUrl>\n" +
                "\t</photoUrls>\n" +
                "\t<tags>\n" +
                "\t\t<Tag>\n" +
                "\t\t\t<id>0</id>\n" +
                "\t\t\t<name>string</name>\n" +
                "\t\t</Tag>\n" +
                "\t</tags>\n" +
                "\t<status>available</status>\n" +
                "</Pet>";


        Response response = RestAssured.given()
                .contentType(ContentType.XML) // Specify the content type of the request
                .accept(ContentType.XML)      // Specify that the response should be in XML
                .body( requestBody )
                .put( url );

        Assert.assertEquals(200, response.statusCode());



    }


    @Test
    public void test_4_deleteOnePetByID(){
        String url = "https://petstore.swagger.io/v2/pet/" + petID;

        Response response = RestAssured.given().delete(url);

        Assert.assertEquals(200, response.statusCode());

    }






}
