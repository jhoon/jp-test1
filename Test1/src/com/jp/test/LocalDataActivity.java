package com.jp.test;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.jp.test.db.DatabaseHelper;
import com.jp.test.db.Movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LocalDataActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	private static final String CLICK_COUNTER_ID = "clickCounterId";
	
//	public static void callMe(Context c, Integer clickCounterId) {
//		Intent intent = new Intent(c, Movies.class);
//		intent.putExtra(CLICK_COUNTER_ID, clickCounterId);
//		c.startActivity(intent);
//	}
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_list);
		try{refreshList();}catch(Exception e){}
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_local, menu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.mniRefreshLocal:
	    	try {
	    		refreshList();
	    	}catch (Exception e){}
	        return true;
	    case R.id.mniNew:
	        goToNew();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	public boolean onPrepareOptionsMenu (Menu menu){
		return true;
	}
	
	private void goToNew(){
		Intent intent = new Intent(getApplicationContext(),LocalInsertActivity.class);
		startActivity(intent);
	}
	
	private void refreshList() throws SQLException{
		Dao<Movies,Integer> dao = getHelper().getMoviesDao();
		QueryBuilder<Movies, Integer> builder = dao.queryBuilder();
		builder.orderBy("id", true);
		List<Movies> movies =dao.query(builder.prepare());
		Log.v(this.getClass().toString(), movies.size()+"");
		ListView lv = (ListView)findViewById(R.id.list_movies_local);
		lv.setAdapter(new LocalAdapter(this, R.layout.list_item, movies));
	}
	
	private class LocalAdapter extends ArrayAdapter<Movies> {
		public LocalAdapter(Context context, int textViewResourceId, List<Movies> items) {
			super(context, textViewResourceId, items);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.list_item, null);
			}
			Movies count = getItem(position);
			fillText(v, R.id.txtListTitle, count.getTitle());
			v.findViewById(R.id.prgProgress).setVisibility(View.GONE);
			return v;
		}

		private void fillText(View v, int id, String text) {
			TextView textView = (TextView) v.findViewById(id);
			textView.setText(text == null ? "" : text);
		}
	}
}
