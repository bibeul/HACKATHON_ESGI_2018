package com.esgi.al.solistrophe.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class ApiClass {

    private static HttpURLConnection con;

    private String url = "http://localhost:8080/";
    private String api_key = null;
    private String auth = null;

    public OutputStream getOutputStream(String httpReq, String route) {
        if(System.getProperty("token") == null){
            System.setProperty("token","");
        }
        try {
            URL myurl = new URL(url + route);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod(httpReq);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("x-access-token", System.getProperty("token"));
            OutputStream os = con.getOutputStream();
            return os;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return null;
    }

    public String getResponse(String httpReq, String route){
        try {
            URL myurl = new URL(url + route);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod(httpReq);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();
            return sb.toString();

        }catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void signIn(String username, String password) {
        JSONObject postData = new JSONObject();
        try {
            if (username.contains("@")){
                postData.put("email", username);
            }
            else {
                postData.put("username", username);
            }
            postData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {

            OutputStream os = getOutputStream("POST", "user/signin");
            if (os == null) {
                con.disconnect();
                return;
            }
            try{
                os.write(postData.toString().getBytes("UTF-8"));
                os.close();

                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(con.getInputStream());

                api_key = jsonNode.get("token").toString();

                auth = jsonNode.get("auth").toString();

            } catch (Exception e){
                api_key = null;
                e.getMessage();
            }

        } finally {
            con.disconnect();
        }
    }

    public void register(String firstname, String lastname, String email, String user, String password, String phone, String address) {
        JSONObject postData = new JSONObject();
        try {
            postData.put("firstname", firstname);
            postData.put("lastname", lastname);
            postData.put("email", email);
            postData.put("username", user);
            postData.put("password", password);
            postData.put("phone", phone);
            postData.put("address", address);
        } catch (JSONException e){
            e.printStackTrace();
        }

        try {

            OutputStream os = getOutputStream("PUT","user/register");
            if (os == null) {
                con.disconnect();
                return;
            }
            os.write(postData.toString().getBytes("UTF-8"));
            os.close();


            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(con.getInputStream());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
    }

    public String getApi_key() {
        return api_key;
    }

    public String getAuth() {
        return auth;
    }
}
