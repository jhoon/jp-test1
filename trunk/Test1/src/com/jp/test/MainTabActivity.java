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

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
//	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
//	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
//	    intent = new Intent().setClass(this, ListadoActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
//	    spec = tabHost.newTabSpec("artists").setIndicator("Artists",
//	                      res.getDrawable(R.drawable.ic_tab_example))
//	                  .setContent(new Intent(this, ListadoActivity.class));
	    tabHost.addTab(tabHost.newTabSpec("artists").setIndicator("Artists",
                res.getDrawable(R.drawable.ic_tab_example))
                .setContent(new Intent(this, ListadoActivity.class)));

	    // Do the same for the other tabs
	    tabHost.addTab(tabHost.newTabSpec("albums").setIndicator("Albums",
                res.getDrawable(R.drawable.ic_tab_example))
                .setContent(new Intent(this, ListadoActivity.class)));

	    tabHost.addTab(tabHost.newTabSpec("songs").setIndicator("Songs",
                res.getDrawable(R.drawable.ic_tab_example))
                .setContent(new Intent(this, ListadoActivity.class)));
//
//	    tabHost.setCurrentTab(0);
	}
}
