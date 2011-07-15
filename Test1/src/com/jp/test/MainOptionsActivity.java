package com.jp.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainOptionsActivity extends Activity {

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_list);
		findViewById(R.id.txtTabbed).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(ListadoActivity.class);
			}
		});
		findViewById(R.id.txtLocal).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(LocalDataActivity.class);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void startActivity(Class clase){
		Intent myIntent = new Intent(this, clase);
		startActivity(myIntent);
	}
}
