package com.example.acl4challange;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

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
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String webData = null;

        try{
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            if(scanner.hasNext()){
                webData =  scanner.next();
            }
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
