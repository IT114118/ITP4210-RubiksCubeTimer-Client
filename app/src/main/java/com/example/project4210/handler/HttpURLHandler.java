package com.example.project4210.handler;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class HttpURLHandler {
    public static final String USBWebServerAddr = "10.0.0.17"; // Change this
    private int responseCode;
    private String returnJson;

    public HttpURLHandler(String urlStr) throws ExecutionException, InterruptedException {
        HttpGetTask postTask = new HttpGetTask();
        returnJson = postTask.execute(urlStr).get();
        System.out.println("DEBUG: " + returnJson);
    }

    public HttpURLHandler(String urlStr, String postParams) throws ExecutionException, InterruptedException {
        HttpPostTask postTask = new HttpPostTask();
        returnJson = postTask.execute(urlStr, postParams).get();
        responseCode = postTask.getResponseCode();
        System.out.println("DEBUG: " + responseCode);
        System.out.println("DEBUG: " + returnJson);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getReturnJson() {
        return returnJson;
    }

    private class HttpGetTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return getHttpURLConnection(params[0]);
        }

        @Override
        protected void onPostExecute(String returnStr) {
            super.onPostExecute(returnStr);
        }

        private String getHttpURLConnection(String urlStr) {
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(urlStr).openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line, response = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }

    private class HttpPostTask extends AsyncTask<String, Void, String> {
        private int responseCode;

        @Override
        protected String doInBackground(String... params) {
            return postHttpURLConnection(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String returnStr) {
            super.onPostExecute(returnStr);
        }

        private String postHttpURLConnection(String urlStr, String postParams) {
            try {
                HttpURLConnection urlConnection;
                urlConnection = (HttpURLConnection) new URL(urlStr).openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.connect();
                DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
                dataOutputStream.writeBytes(postParams);
                dataOutputStream.flush();
                dataOutputStream.close();

                responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String line, response = "";
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    return response;
                } else {
                    return "";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        public int getResponseCode() {
            return responseCode;
        }
    }
}
