package com.wakello.acl4phase1;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class InternetData {
    private InternetData(){}

    public static URL buildURL(String u){
        URL url = null;
        try{
            url=new URL(u);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getData(URL url) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        //HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String webData = null;

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String s = null;
            StringBuilder sb = new StringBuilder();
            while ((s = reader.readLine()) != null) {
                sb.append(s);
            }
            reader.close();
            webData = sb.toString();
        }
        catch (Exception e){
            Log.d("Error: ", e.toString());
        }
        finally {
            connection.disconnect();
        }
        return webData;
    }
}
