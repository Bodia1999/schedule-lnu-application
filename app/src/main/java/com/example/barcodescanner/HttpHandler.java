package com.example.barcodescanner;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    public String sendRequestWithBody(String audience, String dayOfTheWeek, String lessonName, String teacherSurname) throws JSONException {
        String restUrl = "https://yourschedulelnu.herokuapp.com/lesson/findByFilter";
//        String username="myusername";
//        String password="mypassword";
        String daysArray[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        Calendar calendar = Calendar.getInstance();
        int i1 = calendar.get(Calendar.DAY_OF_WEEK);
        String s = daysArray[i1];
        JSONObject user = new JSONObject();
        if (audience != null) {
            user.put("audienceNumber", audience);
        }else {
            user.put("audienceNumber","");
        }
        if (dayOfTheWeek != null) {
            user.put("dayOfTheWeek", audience);
        } else {
            user.put("dayOfTheWeek", s);
        }
        if (teacherSurname!=null){
            user.put("teacherSurname",teacherSurname);
        }
        else {
            user.put("teacherSurname","");
        }
        if (lessonName !=null){
            user.put("lessonName",lessonName);
        }
        else {
            user.put("lessonName","");
        }

//        user.put("email", "davy@gmail.com");
        String jsonData = user.toString();
        HttpHandler httpPostReq = new HttpHandler();
        HttpPost httpPost = httpPostReq.createConnectivity(restUrl);
        return httpPostReq.executeReq(jsonData, httpPost);
    }

    private HttpPost createConnectivity(String restUrl) {
        HttpPost post = new HttpPost(restUrl);
//        String auth=new StringBuffer(username).append(":").append(password).toString();
//        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
//        String authHeader = "Basic " + new String(encodedAuth);
//        post.setHeader("AUTHORIZATION", authHeader);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setHeader("X-Stream", "true");
        return post;
    }

    private String executeReq(String jsonData, HttpPost httpPost) {
        try {
            return executeHttpRequest(jsonData, httpPost);
        } catch (UnsupportedEncodingException e) {
            System.out.println("error while encoding api url : " + e);
        } catch (IOException e) {
            System.out.println("ioException occured while sending http request : " + e);
        } catch (Exception e) {
            System.out.println("exception occured while sending http request : " + e);
        } finally {
            httpPost.releaseConnection();
        }
        return null;
    }

    private String executeHttpRequest(String jsonData, HttpPost httpPost) throws UnsupportedEncodingException, IOException {
        HttpResponse response = null;
        String line = "";
        StringBuffer result = new StringBuffer();
        httpPost.setEntity(new StringEntity(jsonData));
        HttpClient client = HttpClientBuilder.create().build();
        response = client.execute(httpPost);
        System.out.println("Post parameters : " + jsonData);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
        return result.toString();
    }

//    public String requestWithBody(String reqUrl, Integer audience){
//        String daysArray[] = {"Sunday","Monday","Tuesday", "Wednesday","Thursday","Friday", "Saturday"};
//        Calendar calendar = Calendar.getInstance();
//        int i1 = calendar.get(Calendar.DAY_OF_WEEK);
//        String s = daysArray[i1];
//        String response = null;
//        try {
////            URL url = new URL(reqUrl);
////            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////
//            List<NameValuePair> nameValuePairs = new ArrayList<>();
//
////            conn.setRequestMethod("POST");
//            HttpPost httpPost = new HttpPost(reqUrl);
//            nameValuePairs.add(new BasicNameValuePair("audienceNumber" ,audience.toString()));
//            nameValuePairs.add(new BasicNameValuePair("dayOfWeek" ,audience.toString()));
//            R.id.dateOn
//            httpPost.setEntity();
//            // read the response
//            InputStream in = new BufferedInputStream(conn.getInputStream());
//            response = convertStreamToString(in);
//        } catch (MalformedURLException e) {
//            Log.e(TAG, "MalformedURLException: " + e.getMessage());
//        } catch (ProtocolException e) {
//            Log.e(TAG, "ProtocolException: " + e.getMessage());
//        } catch (IOException e) {
//            Log.e(TAG, "IOException: " + e.getMessage());
//        } catch (Exception e) {
//            Log.e(TAG, "Exception: " + e.getMessage());
//        }
//        return response;
//    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
