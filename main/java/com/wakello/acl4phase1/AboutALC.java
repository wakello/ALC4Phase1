package com.wakello.acl4phase1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AboutALC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{
            URL linkURL = InternetData.buildURL("https://xandela.com/alc/");
            new GetAbout().execute(linkURL);
        }
        catch (Exception e){
            Log.d("Error: ", e.getMessage());
        }
    }

    public class GetAbout extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;

            TextView txtAbout = (TextView) findViewById(R.id.text_about);
            HttpsURLConnection connection=null;

            try{
                connection = (HttpsURLConnection) searchURL.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                String s = null;
                StringBuilder sb = new StringBuilder();
                while ((s = reader.readLine()) != null) {
                    sb.append(s);
                }
                reader.close();
                result = sb.toString();
                txtAbout.setText("Browsing");
            }
            catch (IOException e){
                txtAbout.setText("Error");
                Log.e("Error: ",e.getMessage());
            }
            finally {
                connection.disconnect();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result){
            TextView txtAbout = (TextView) findViewById(R.id.text_about);
            txtAbout.setText(result);
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }
    }
}
