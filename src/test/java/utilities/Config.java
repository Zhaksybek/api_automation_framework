package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    // To be able to read configurations.properties

    private static Properties properties;

    static {

        try{
            FileInputStream file = new FileInputStream("configurations.properties");
            properties = new Properties();
            properties.load(file);
            file.close();
        }
        catch (IOException e){
            System.out.println("Configuration file not found");
            //e.printStackTrace();
        }
    }


    public static String getProperty (String key) {
        return properties.getProperty(key);
    }






}
