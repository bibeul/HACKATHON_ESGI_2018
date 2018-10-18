package com.esgi.al.solistrophe.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.*;

public class ApiClass {
    private static String url = "http://localhost:3000/api/";
    private String api_key = null;
    private static String auth = null;

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
                        .header("Authorization", "Bearer ")
                        .post(formBody)
                        .build();
            } else {
                request = new Request.Builder()
                        .url(url)
                        .header("Authorization", "Bearer ")
                        .build();
            }
        } else if (type.equals("GET")) {
            request = new Request.Builder()
                    .url(url)
                    .header("Authorization", "Bearer ")
                    .build();
        } else if (type.equals("PATCH")) {
            if (formBody != null) {
                request = new Request.Builder()
                        .url(url)
                        .header("Authorization", "Bearer ")
                        .patch(formBody)
                        .build();
            } else {
                request = new Request.Builder()
                        .url(url)
                        .header("Authorization", "Bearer ")
                        .method("PATCH", null)
                        .build();
            }
        } else if (type.equals("PUT")) {
            request = new Request.Builder()
                    .url(url)
                    .header("Authorization", "Bearer ")
                    .put(formBody)
                    .build();
        } else if (type.equals("DELETE")) {
            request = new Request.Builder()
                    .url(url)
                    .header("Authorization", "Bearer ")
                    .delete()
                    .build();
        }
        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Objects.requireNonNull(response).code() != 200){
            return null;
        }else{
            try {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }finally {
                response.body().close();
            }
        }
    }

    public static String connection(String email, String password) {
        String uri = url + "Accounts";
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
            auth = response;
            return response;
        }
    }

    public String getApi_key() {
        return api_key;
    }

    public String getAuth() {
        return auth;
    }
}
