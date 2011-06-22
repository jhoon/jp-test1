package com.jp.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListadoActivity extends ListActivity {
	TextView txt;
	String[] movieList;
	String[] movieId;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        connect("http://jptest.nfshost.com/testapp/exm.php");
        
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, movieList));

        ListView lv = getListView();
        lv.setTextFilterEnabled(false);
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
        	  //Intent myIntent = new Intent(ListadoActivity.this, ListadoActivity.class);
              //startActivity(myIntent);
          }
        });
    }
    
    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
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
    
    private void connect(String url){
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url); 
        HttpResponse response;
 
        try {
            response = httpclient.execute(httpget);
 
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    JSONObject myAwway = new JSONObject(convertStreamToString(instream));
                    JSONArray movies = myAwway.getJSONArray("movies");
                    //"Found: " + movies.length() + " movies";
                    movieList = new String[movies.length()];
                    movieId = new String[movies.length()];
                    for (int i = 0; i < movies.length(); i++) {
                    	JSONObject movie = movies.getJSONObject(i);
                        movieList[i] = movie.getString("title");
                        movieId[i] = movie.getString("id"); 
                    }
                    instream.close();
                }
            }
        }
        catch (IOException  ex) {
        }
        catch (JSONException ex){
        }
    }
}