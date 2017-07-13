package com.example.brenner.fidelity_wallet;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.util.Log;

import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.FormBody;

/**
 * Created by Brenner on 7/7/2017.
 */

public class PostRequest{
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();
    private static String secretKey;
    private int amount;
    private String balance;
    private String status;
    //private String fileName = "C:\\Users\\Brenner\\AndroidStudioProjects\\Fidelity_Wallet\\secretKey.txt";
    //private File file = new File(fileName);
    //private FileWriter writer;

    public PostRequest() {

    }
    public void setSecretKey(String k){
        secretKey = k;
    }
    public String getSecretKey(){
        return secretKey;
    }
    /*
    Method to handle post request for logging in
     */
    public String postLogin(String userName, String passWord) throws IOException {

        FormBody.Builder bodyBuilder = new FormBody.Builder()
                .add("username", userName)
                .add("password", passWord);
        RequestBody body = bodyBuilder.build();
        Request request = new Request.Builder()
                .url("http://fidwallet.herokuapp.com/api/login")
                //.url("http://fidwallet.herokuapp.com/api/balance") for balance send just the secret key
                //.url("http://fidwallet.herokuapp.com/api/sendMoney") send just the secretkey, type(send || request), username
                .post(body)
                .build();
        Log.d("DEBUG: ", "attempting Post Request");
        Response response = client.newCall(request).execute();
        //TODO what to do if not successful
        if(!response.isSuccessful()){
            Log.d("DEBUG: ", "PostRequest was not successful");
        }
        else{
            Log.d("DEBUG: ","PostRequest was successful");
            try{
                JSONObject mainObj = new JSONObject(response.body().string());
                setSecretKey(mainObj.getString("secretkey"));
                Log.d("We're Returning: ", secretKey);
                return secretKey;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }
    /*
    Method for handling post request for sending money to another user
     */
    public void postSendMoney(String secretkey, String username, String amount_) throws IOException{
        //TODO post request for sending money

        FormBody.Builder bodyBuilder = new FormBody.Builder()
                .add("secretkey", secretkey)
                .add("type", "send")
                .add("username", username)
                .add("amount", amount_);
        RequestBody body = bodyBuilder.build();
        Request request = new Request.Builder()
                //.url("http://fidwallet.herokuapp.com/api/login")
                // for balance send just the secret key
                //.url("http://fidwallet.herokuapp.com/api/balance")
                //send just the secretkey, type(send || request), username
                .url("http://fidwallet.herokuapp.com/api/sendMoney")
                .post(body)
                .build();
        Log.d("DEBUG: ", "attempting Post Request");
        Response response = client.newCall(request).execute();
        //TODO what to do if not successful
        if(!response.isSuccessful()){
            Log.d("DEBUG: ", "PostRequest was not successful");
        }
        else{
            Log.d("DEBUG: ","PostRequest was successful");
            try{
                JSONObject mainObj = new JSONObject(response.body().string());
                status = mainObj.getString("status");
                Log.d("DEBUG","postSendMoney Status: " + status);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /*
   Method for handling post request for sending money to another user
    */
    public void postRequestMoney(String secretkey, String username, String amount_) throws IOException{
        //TODO post request for sending money

        FormBody.Builder bodyBuilder = new FormBody.Builder()
                .add("secretkey", secretkey)
                .add("type", "request")
                .add("username", username)
                .add("amount", amount_);
        RequestBody body = bodyBuilder.build();
        Request request = new Request.Builder()
                //.url("http://fidwallet.herokuapp.com/api/login")
                // for balance send just the secret key
                //.url("http://fidwallet.herokuapp.com/api/balance")
                //send just the secretkey, type(send || request), username
                .url("http://fidwallet.herokuapp.com/api/sendMoney")
                .post(body)
                .build();
        Log.d("DEBUG: ", "attempting Post Request");
        Response response = client.newCall(request).execute();
        //TODO what to do if not successful
        if(!response.isSuccessful()){
            Log.d("DEBUG: ", "PostRequest was not successful");
        }
        else{
            Log.d("DEBUG: ","PostRequest was successful");
            try{
                JSONObject mainObj = new JSONObject(response.body().string());
                //TODO: what is the key so that we can get the amount?
                status = mainObj.getString("status");
                Log.d("DEBUG ", "postRequestMoney Status: " + status);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /*
    Method for handling post request for getting users current balance
     */
    public String postGetCurrBalance(String secretKey)throws IOException{
        Log.d("DEBUG ", "Secretkey -> getCurrBal " + secretKey);
        FormBody.Builder bodyBuilder = new FormBody.Builder()
                .add("secretkey", secretKey);
        RequestBody body = bodyBuilder.build();
        Request request = new Request.Builder()
                //.url("http://fidwallet.herokuapp.com/api/login")
                //for balance send just the secret key
                .url("http://fidwallet.herokuapp.com/api/balance")
                //.url("http://fidwallet.herokuapp.com/api/sendMoney") send just the secretkey, type(send || request), username
                .post(body)
                .build();
        Log.d("DEBUG: ", "attempting Post Request");
        Response response = client.newCall(request).execute();
        //TODO what to do if not successful
        if(!response.isSuccessful()){
            Log.d("DEBUG: ", "PostRequest was not successful");
        }
        else{
            Log.d("DEBUG: ","PostRequest was successful");
            try{
                JSONObject mainObj = new JSONObject(response.body().string());
                balance = mainObj.getString("balance");
                Log.d("We're Returning: ", balance);
                return balance;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

}
