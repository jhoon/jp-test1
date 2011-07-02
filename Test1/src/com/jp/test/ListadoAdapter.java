package com.jp.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListadoAdapter extends BaseAdapter {

	private Context mContext;
	private String[] mTitles;
	private String[] mIcons;
	private String[] mIds;
	private LayoutInflater mLayoutInflater;
	
	public ListadoAdapter(Context cxt){
		mContext = cxt;
		mTitles = ((ListadoActivity)cxt).movieList;
		mIds = ((ListadoActivity)cxt).movieId;
		mIcons = ((ListadoActivity)cxt).movieIcon;
	}
	
	public int getCount() {
		return mIds.length;
	}

	public Object getItem(int arg0) { return null; }

	public long getItemId(int arg0) { return 0; }

	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		if(v==null){
			if(mLayoutInflater==null) {
				mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			v = mLayoutInflater.inflate(R.layout.list_item, null);
		}
		
		TextView t = (TextView) v.findViewById(R.id.txtListTitle);
		t.setText(mTitles[position]);
		
		ImageView i = (ImageView) v.findViewById(R.id.imgListPelicula);
		
		try {
			i.setImageBitmap(ListadoActivity.getBitmapFromUrl(mIcons[position]));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//for accessibility
		v.setContentDescription(mTitles[position]);

		return v;
	}
/*
	// AsyncTask <Params, Progress, Result>
	private class DownloadImageTask extends AsyncTask<String, Void, Integer> {
	     protected Integer doInBackground(String... strBitmapUrl){
	         try {
				ListadoActivity.getBitmapFromUrl(strBitmapUrl[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
	         return 0;
	     }

	     protected void onPostExecute(Integer result) {
	         //mImageView.setImageBitmap(result);
	     }
	 }*/

}
