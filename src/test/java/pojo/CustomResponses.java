package pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponses {

    // Bank Account Response body
    private String id;
    private String bank_account_name;
    private String description;
    private double balance;


    // Seller response body
    private int seller_id;
    private String company_name;
    private String email;




}
