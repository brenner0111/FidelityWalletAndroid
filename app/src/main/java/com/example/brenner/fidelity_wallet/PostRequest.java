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
    //private String fileName = "C:\\Users\\Brenner\\AndroidStudioProjects\\Fidelity_Wallet\\secretKey.txt";
    //private File file = new File(fileName);
    //private FileWriter writer;

    public PostRequest() {

    }
    public void setSecretKey(String k){
        secretKey = k;
    }
    public String getSecretKey(String k){
        return secretKey;
    }
    /*
    Method to handle post request for logging in
     */
    public String postLogin(String userName, String passWord) throws IOException {
        /*try{
            writer = new FileWriter(file);
        }catch(Exception e){
            e.printStackTrace();
        }*/

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
        }
        try{
            //Log.d("DEBUG ", response.body().string());
            JSONObject mainObj = new JSONObject(response.body().string());
            setSecretKey(mainObj.getString("secretkey"));
            //writer.append(secretKey);
            Log.d("We're Returning: ", secretKey);
            return secretKey;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
