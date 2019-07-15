package com.example.acl4challange;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class AboutALC extends AppCompatActivity {

    private ProgressBar dataLoadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dataLoadingProgress = (ProgressBar) findViewById(R.id.pbLoading);

        try{
            URL linkURL = InternetData.buildURL("https://andela.com/alc/");
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
            try{
                result = InternetData.getData(searchURL);
            }
            catch (IOException e){
                Log.e("Error: ",e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result){
            TextView txtResult = (TextView) findViewById(R.id.txtAbout);
            dataLoadingProgress.setVisibility(View.INVISIBLE);
            txtResult.setText(result);
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dataLoadingProgress.setVisibility(View.VISIBLE);
        }
    }

}
