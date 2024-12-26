package pojo;


import lombok.Data;

@Data // It will create Getter and setter method automatically
public class RequestBody {

    /**
     *  RequestBody => purpose of this class is described all variables from our request body
     *  We use this to set value our variables (Set all request body data)
     */

    // Bank account Request Body
    private String type_of_pay; // Encapsulation
    private String bank_account_name;
    private String description;
    private double balance;


    // Seller Controller
    private String company_name;
    private String seller_name;
    private String email;
    private String phone_number;
    private String address;



}
