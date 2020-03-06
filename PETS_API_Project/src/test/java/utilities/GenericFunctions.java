///**
// Author: Sudhakar Madamala
// History: New functions to handle the HTTPs status code and verification. Handle response from local file
//*
package utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import org.json.simple.parser.ParseException;
import steps.APIServices;

import java.util.Properties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import static java.lang.System.out;

public class GenericFunctions{

    private static APIServices apis = new APIServices();
    private static Response response;
    private static String url;
    private static String serviceStatus;
    private int petStatusCount;
    private static JSONArray jsonList;
    //**
    //Method to read the properties file
    //*
    public static String getConfigValue(String key)throws Exception{
        FileReader reader = null;
        try {
            reader = new FileReader("./src/test/java/resources/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String keyValue = prop.getProperty(key);
        return keyValue;
    }
    //**
    //Method to check the HTTP status of the given URL and set the status flag
    //*
    public static void getAPIServiceStatus()throws Exception{
        url = null;
        String baseURL = getConfigValue("base.url");
        String swaggerEngPoint = getConfigValue("swagger.endpoint");

        url = baseURL + swaggerEngPoint;
        response = apis.getMethod(url);
        out.println("API Service HTTP Ping Status code: "+response.getStatusCode());
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            serviceStatus = "true";
        } else {
            serviceStatus = "false";
        }
    }
    //**
    //Method to construct required URL based on the service status flag
    //*
    public static String getServiceURL()throws Exception{
        url = null;
        String endPoint = getConfigValue("petstatus.endpoint");
        String queryParameter = getConfigValue("petstatus.query.parameter");
        if (serviceStatus.equals("true")) {
            out.println("HTTP service ping successful, connecting to the swagger URL");
            url = getConfigValue("base.url") + endPoint + queryParameter;
        } else {
            out.println("HTTP service not responding ... Re-directing the service URL to local.");
            url = "./src/test/java/resources/pets.json";
        }
        return url;
    }
    //**
    //Method to get pet status response based on the status provided.
    // If the swagger URL is not responding the method will read the local json file from resources, and extract the payload information
    //*
    public static void getPetsByStatus(PetStatus petStatus) {
        if (serviceStatus.equals("true")) {
            String apiURL = url + petStatus.getName();
            APIServices apiServices = new APIServices();
            response = apiServices.getMethod(apiURL);
        } else {
            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader(url))
            {
                Object obj = jsonParser.parse(reader);
                jsonList = (JSONArray) obj;

            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
    }
    //**
    //This method validate the pet response available, and provide the count
    //*
    public static int validatePETResponse(String petName, String petStat, String petCount){
        int count=0;
        if (serviceStatus.equals("true")) {
            if (response.contentType().equalsIgnoreCase("application/json")) {
                JsonArray entries = (JsonArray) new JsonParser().parse(response.asString());
                for (int i = 0; i < entries.size(); i++) {
                    JsonElement name = ((JsonObject) entries.get(i)).get("name");
                    JsonElement status = ((JsonObject) entries.get(i)).get("status");
                    if (name != null) {
                        if (name.getAsString().equalsIgnoreCase(petName) && status.getAsString().equalsIgnoreCase(petStat)) {
                            count++;
                        }
                    }
                }
            }
        }   else {
            JsonArray entries = (JsonArray) new JsonParser().parse(String.valueOf(jsonList));
            for(int i=0;i<entries.size();i++){
                JsonElement name = ((JsonObject)entries.get(i)).get("name");
                JsonElement status = ((JsonObject)entries.get(i)).get("status");
                if(name.getAsString().equalsIgnoreCase(petName) && status.getAsString().equalsIgnoreCase(petStat)){
                    count++;
                }
            }
        }
        out.println("**** Pet Status Count: The no. of records meeting the criteria are: "+count);
        return count;
    }

}