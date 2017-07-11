package com.example.brenner.fidelity_wallet;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.FormBody;

/**
 * Created by Brenner on 7/7/2017.
 */

public class PostRequest {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();
    String fileName = "C:\\Users\\Brenner\\AndroidStudioProjects\\Fidelity_Wallet\\secretKey.txt";
    File file = new File(fileName);
    FileWriter writer;

    public PostRequest(){

    }
    /*
    Method to handle post request for logging in
     */
    public String postLogin(String userName, String passWord) throws IOException {
        try{
            writer = new FileWriter(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        String secretKey = "";
        FormBody.Builder bodyBuilder = new FormBody.Builder()
                .add("username", userName)
                .add("password", passWord);
        RequestBody body = bodyBuilder.build();
        Request request = new Request.Builder()
                .url("/api/login/http://fidwallet.herokuapp.com/user/login")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        //TODO what to do if not successful
        if(!response.isSuccessful()){

        }
        try{
            JSONObject mainObj = new JSONObject(response.body().string());
            secretKey = mainObj.getString("secretkey");
            writer.append(secretKey);
            return secretKey;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response.body().string();
    }
    /*
    Method for handling post request for sending money to another user
     */
    public String postSendMoney(String type, String username, String amount, String secretkey) throws IOException{
        //TODO post request for sending money
        return "";
    }
    /*
    Method for handling post request for getting users current balance
     */
    public String postGetCurrBalance(String secretKey){
        return "";
    }

}
