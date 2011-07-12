package com.jp.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

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
		ImageView imgListPelicula = (ImageView) v.findViewById(R.id.imgListPelicula);
		ProgressBar prgProgress = (ProgressBar) v.findViewById(R.id.prgProgress);
		
		t.setText(mTitles[position]);
		imgListPelicula.setTag(mIcons[position]);
		
		// se ejecuta el AsyncTask para obtener los datos
		new ListadoAdapterTask().execute(imgListPelicula,prgProgress);
		v.setContentDescription(mTitles[position]);

		return v;
	}

	private class ListadoAdapterTask extends AsyncTask<View, Void, Bitmap>{
		ImageView imgAid = null;
		ProgressBar prgLoad = null;
		
		protected Bitmap doInBackground(View... views){
			this.imgAid = (ImageView)views[0];
			this.prgLoad = (ProgressBar)views[1];
			Bitmap image = null;
			try {
				image = ListadoActivity.getBitmapFromUrl((String)imgAid.getTag());
			} catch (Exception e) {
				e.printStackTrace();
				Log.v("Adapter","adapter bitmap #fail!");
			}
			return image;
		}
		
		protected void onPostExecute(Bitmap image){
			if(image != null){
				imgAid.setScaleType(ScaleType.FIT_CENTER);
				imgAid.setImageBitmap(image);
				prgLoad.setVisibility(View.GONE);
				imgAid.setVisibility(View.VISIBLE);
			}
		}
		
	}
}
