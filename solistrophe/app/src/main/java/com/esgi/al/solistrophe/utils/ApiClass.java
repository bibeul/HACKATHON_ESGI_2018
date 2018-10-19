package com.esgi.al.solistrophe.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiClass {
    private static String url = "http://10.33.0.108:3000/api/";
    private String api_key = null;
    private static JsonNode auth = null;
    private static JsonNode resp = null;
    private static ObjectMapper mapper = new ObjectMapper();

    public static String setOkHttpRequest(String url, RequestBody formBody, Boolean connection, String type) {
        OkHttpClient client = new OkHttpClient();
        Request request = null;

        if (type.equals("POST")) {
            if (connection && formBody != null) {
                request = new Request.Builder()
                        .url(url)
                        .post(formBody)




                        .build();
            } else if (formBody != null) {
                request = new Request.Builder()
                        .url(url)
                        .post(formBody)
                        .build();
            } else {
                request = new Request.Builder()
                        .url(url)
                        .build();
            }
        } else if (type.equals("GET")) {
            if(formBody == null){
                request = new Request.Builder()
                        .url(url)
                        .build();
            }else{
                request = new Request.Builder()
                        .url(url)
                        .post(formBody)
                        .build();
            }
        } else if (type.equals("PATCH")) {
            if (formBody != null) {
                request = new Request.Builder()
                        .url(url)
                        .patch(formBody)
                        .build();
            } else {
                request = new Request.Builder()
                        .url(url)
                        .method("PATCH", null)
                        .build();
            }
        } else if (type.equals("PUT")) {
            request = new Request.Builder()
                    .url(url)
                    .put(formBody)
                    .build();
        } else if (type.equals("DELETE")) {
            request = new Request.Builder()
                    .url(url)
                    .delete()
                    .build();
        }

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("notok");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                resp = mapper.readTree(response.body().string());
            }
        });
        return null;
    }

    public static String connection(String email, String password) {
        String uri = url + "Accounts/login";
        RequestBody formBody;

        if(email.contains("@")){
            formBody = new FormBody.Builder()
                    .add("email", email)
                    .add("password", password)
                    .build();
        }
        else {
            formBody = new FormBody.Builder()
                    .add("username", email)
                    .add("password", password)
                    .build();
        }

        String response = setOkHttpRequest(uri, formBody, true, "POST");

        if(response == null){
            return null;
        }else{
            return response;
        }
    }

    public static String register(String firstname, String lastname, String phone, String address,
                                  String username, String email, String password) {
        String uri = url + "Accounts/";
        RequestBody formBody;

        formBody = new FormBody.Builder()
                .add("firstname", firstname)
                .add("lastname", lastname)
                .add("phoneNumber", phone)
                .add("address", address)
                .add("username", username)
                .add("email", email)
                .add("password", password)
                .build();

        String response = setOkHttpRequest(uri, formBody, true, "POST");
        if(response == null){
            return null;
        }else{
            return response;
        }
    }

    public static String findAllMatches(String lng, String lat) {
        String uri = url + "Accounts/findAllMatches";
        RequestBody formBody;

        formBody = new FormBody.Builder()
                .add("longitude", lng)
                .add("latitude", lat)
                .build();

        String response = setOkHttpRequest(uri, formBody, false, "POST");
        if(response == null){
            return null;
        }else{
            return response;
        }
    }

    public static String getMatchesSinister(String id) {
        String uri = url + "Accounts/" + id + "/sinisters?access_token=" + auth.get("id");

        String response = setOkHttpRequest(uri, null, false, "GET");
        if(response == null){
            return null;
        }else{
            return response;
        }
    }

    public static String getAccountInformation(String id) {
        String uri = url + "Accounts/" + id + "?access_token=" + auth.get("id");

        String response = setOkHttpRequest(uri, null, false, "GET");
        if(response == null){
            return null;
        }else{
            return response;
        }
    }

    public static String declaredSinister(String name, String description, String severity, String userId) {
        String uri = url + "Sinisters/";
        RequestBody formBody;

        formBody = new FormBody.Builder()
                .add("name", name)
                .add("description", description)
                .add("severity", severity)
                .add("state", "0")
                .add("accountId", userId)
                .build();

        String response = setOkHttpRequest(uri, formBody, true, "POST");

        if(response == null){
            return null;
        }else{
            return response;
        }
    }

    public static String declaredService(String name, String description) {
        String uri = url + "services/";
        RequestBody formBody;

        formBody = new FormBody.Builder()
                .add("name", name)
                .add("description", description)
                .build();

        String response = setOkHttpRequest(uri, formBody, true, "POST");

        if(response == null){
            return null;
        }else{
            return response;
        }
    }

    public String getApi_key() {
        return api_key;
    }

    public JsonNode getAuth() {
        return auth;
    }

    public void setAuth(JsonNode jsonNode){
        auth = jsonNode;
    }

    public JsonNode getResp() {
        return resp;
    }
}
