package com.jp.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListadoActivity extends ListActivity {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_ICON = "icon";
	public static final String KEY_TITLE = "titulo";
	private static final String url = "http://restmocker.bitzeppelin.com/api/datatest/peliculas.json";
	
	
	String[] movieList;
	String[] movieId;
	String[] movieIcon;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		JSONArray movies = connect(ListadoActivity.url);
		
		try {
			// "Found: " + movies.length() + " movies";
			movieList = new String[movies.length()];
			movieId = new String[movies.length()];
			movieIcon = new String[movies.length()];
			for (int i = 0; i < movies.length(); i++) {
				JSONObject movie;
				movie = movies.getJSONObject(i);
				movieId[i] = movie.getString("id");
				movieList[i] = movie.getString("nombre");
				movieIcon[i] = movie.getString("icono");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		setListAdapter(new ListadoAdapter(this));

		ListView lv = getListView();
		/*		lv.setTextFilterEnabled(false);*/
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent myIntent = new Intent(ListadoActivity.this,
						DetalleActivity.class);
				myIntent.putExtra(ListadoActivity.KEY_ROWID, movieId[position]);
				myIntent.putExtra(ListadoActivity.KEY_TITLE,movieList[position]);
				myIntent.putExtra(ListadoActivity.KEY_ICON, movieIcon[position]);
				// toast("id: "+id+" position: "+position);
				startActivity(myIntent);

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

	protected static JSONArray connect(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response;
		JSONArray arResp = null;
		try {
			response = httpclient.execute(httpget);

			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					arResp = new JSONArray(convertStreamToString(instream));
					instream.close();
				}
			}
		} catch (Exception ex) {

		}
		return arResp;
	}
	
	public static Bitmap getBitmapFromUrl(String url) throws Exception{
		URL ulrn = new URL(url);
	    HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
	    InputStream is = con.getInputStream();
		return BitmapFactory.decodeStream(is);
	}
}