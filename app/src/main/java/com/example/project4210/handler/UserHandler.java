package com.example.project4210.handler;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

public class UserHandler {
    private final String USBWebServerIP = "10.0.0.18";
    private String token, error;

    public UserHandler() {

    }

    public boolean login(String username, String password) {
        try {
            String postParams = "username=" + username + "&password=" + password;
            HttpURLHandler handler = new HttpURLHandler("http://" + USBWebServerIP + ":8080/login.php", postParams);
            switch (handler.getResponseCode()) {
                case 200:
                    try {
                        JSONObject json = new JSONObject(handler.getReturnJson());
                        if (String.valueOf(json.get("status")).equals("success")) {
                            token = String.valueOf(json.get("token"));
                            return true;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    error = "Server error, please try again later"; // Fail to get token
                    return false;
                case 503:
                    error = "Couldn't find your account";
                    return false;
                case 504:
                    error = "Wrong password";
                    return false;
                default: // 500, 502, 505, 506
                    error = "Server error, please try again later";
                    return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        error = "Fail to connect to the server";
        return false;
    }

    public boolean signup(String username, String password) {
        try {
            String postParams = "username=" + username + "&password=" + password;
            HttpURLHandler handler = new HttpURLHandler("http://" + USBWebServerIP + ":8080/signup.php", postParams);
            switch (handler.getResponseCode()) {
                case 200:
                    try {
                        JSONObject json = new JSONObject(handler.getReturnJson());
                        if (String.valueOf(json.get("status")).equals("success")) {
                            token = String.valueOf(json.get("token"));
                            return true;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    error = "Server error, please try again later"; // Fail to get token
                    return false;
                case 503:
                    error = "That username is taken, please try another";
                    return false;
                default: // 500, 502, 504, 505
                    error = "Server error, please try again later";
                    return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        error = "Fail to connect to the server";
        return false;
    }

    public boolean checkToken(String username, String token) {
        try {
            String postParams = "username=" + username + "&token=" + token;
            HttpURLHandler handler = new HttpURLHandler("http://" + USBWebServerIP + ":8080/token.php", postParams);
            switch (handler.getResponseCode()) {
                case 200:
                    try {
                        JSONObject json = new JSONObject(handler.getReturnJson());
                        if (String.valueOf(json.get("status")).equals("success")) {
                            this.token = String.valueOf(json.get("token"));
                            return true;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                case 503:
                case 504:
                    error = "Session expired, please login again";
                    return false;
                default: // 500, 502
                    error = "Server error, please try again later";
                    return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        error = "Fail to connect to the server";
        return false;
    }

    public String getError() {
        return error;
    }

    public String getToken() {
        return token;
    }
}
