package com.jp.test;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleActivity extends Activity {
	private String url = "http://jptest.nfshost.com/testapp/$.json";
    private String mRowId;
    
    
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle);
        //setTitle(R.string.edit_note);
        
        String strPelicula = "";
        Bundle extras = getIntent().getExtras();
        mRowId = "";
        if (extras != null) {
            mRowId = extras.getString(ListadoActivity.KEY_ROWID);
            strPelicula= extras.getString(ListadoActivity.KEY_TITLE);
        }
        
        try{
			JSONArray objeto = ListadoActivity.connect(url.replace("$", mRowId));
			JSONObject pela = objeto.getJSONObject(0);
			TextView content;
			content = (TextView)findViewById(R.id.txtUrl);
			content.setText( mRowId+" - url: "+url.replace("$", mRowId));
			content = (TextView)findViewById(R.id.txtTitle);
			content.setText(strPelicula);
			content = (TextView)findViewById(R.id.txtDirector);
			content.setText(pela.getString("director"));
			content = (TextView)findViewById(R.id.txtGenre);
			content.setText(pela.getString("genero"));
			content = (TextView)findViewById(R.id.txtRating);
			content.setText(pela.getString("calificacion"));
			content = (TextView)findViewById(R.id.txtCast);
			content.setText(pela.getString("actores"));
			content = (TextView)findViewById(R.id.txtPremiereDate);
			content.setText(pela.getString("fecha-estreno"));
			content = (TextView)findViewById(R.id.txtRunningTime);
			content.setText(pela.getString("duracion"));
		}catch(Exception e){
			Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT);
		}
	}
}
