package pojo;

public class RequestBody {

    /**
     *  RequestBody => purpose of this class is described all variables from our request body
     *  We use this to set value our variables (Set all request body data)
     */

    private String type_of_pay;
    private String bank_account_name;
    private String description;
    private double balance;


    public String getType_of_pay() {
        return type_of_pay;
    }

    public void setType_of_pay(String type_of_pay) {
        this.type_of_pay = type_of_pay;
    }

    public String getBank_account_name() {
        return bank_account_name;
    }

    public void setBank_account_name(String bank_account_name) {
        this.bank_account_name = bank_account_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
