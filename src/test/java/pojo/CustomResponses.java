package pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponses {

    // Bank Account Response body
    private String id;
    private String bank_account_name;
    private String description;
    private double balance;


    // Seller response body
    private CustomResponses[] responses;
    private int seller_id;
    private String seller_name;
    private String company_name;
    private String email;


    // Product Controller
    private int product_id;
    private String product_title;
    private double product_price;
    private int service_type_id;
    private int category_id;
    private String product_description;




}
