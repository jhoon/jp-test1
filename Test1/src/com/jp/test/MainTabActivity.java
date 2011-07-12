package com.jp.test;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainTabActivity extends TabActivity {
	public void onCreate(Bundle savedInstanceState){
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tab_main);

	    Resources res = getResources(); // referencia para obtener los Drawables para las pestañas
	    TabHost tabHost = getTabHost();  // el TabHost del Activity

	    // Se agregan los tabs deseados
	    tabHost.addTab(tabHost.newTabSpec("artists").setIndicator("Artists",
                res.getDrawable(R.drawable.ic_tab_example))
                .setContent(new Intent(this, ListadoActivity.class)));

	    tabHost.addTab(tabHost.newTabSpec("albums").setIndicator("Albums",
                res.getDrawable(R.drawable.ic_tab_example))
                .setContent(new Intent(this, ListadoActivity.class)));

	    tabHost.addTab(tabHost.newTabSpec("songs").setIndicator("Songs",
                res.getDrawable(R.drawable.ic_tab_example))
                .setContent(new Intent(this, ListadoActivity.class)));
	}
}
