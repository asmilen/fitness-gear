package com.fitnessgear;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import com.fitnessgear.adapter.NavigationDrawerAdapter;
import com.fitnessgear.database.DataBaseHelper;
import com.fitnessgear.model.NavDrawerItem;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.InputQueue.Callback;
import android.view.SurfaceHolder.Callback2;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mMenuTitle;
    
    private TypedArray navMenuIcons;
    
    private ArrayList<NavDrawerItem> navDrawerItems;
	private NavigationDrawerAdapter adapter;
	
	//database
	public static DataBaseHelper dbHelper;
	public static SQLiteDatabase db = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Khoi tao database
		dbHelper = new DataBaseHelper(this);
		
		try {
			dbHelper.createDataBase();
			db = dbHelper.getReadableDatabase();
		    //Toast.makeText(this, c.getString(c.getColumnIndex("PlanName")), Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Get title of activity
		mTitle = mDrawerTitle = getTitle();
		//Get title resources from string.xml
		mMenuTitle = getResources().getStringArray(R.array.menu_array);
		//Get name's icon resources from string.xml
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        //Define a ArrayList of NavDrawerItem class
        navDrawerItems = new ArrayList<NavDrawerItem>();
        
        // adding nav drawer items to array
 		//Home
 		navDrawerItems.add(new NavDrawerItem(mMenuTitle[0], navMenuIcons.getResourceId(0, -1)));
 		// Start Work Out
 		navDrawerItems.add(new NavDrawerItem(mMenuTitle[1], navMenuIcons.getResourceId(1, -1)));
 		// Find A Plan
 		navDrawerItems.add(new NavDrawerItem(mMenuTitle[2], navMenuIcons.getResourceId(2, -1), true, "22"));
 		// Execise Library
 		navDrawerItems.add(new NavDrawerItem(mMenuTitle[3], navMenuIcons.getResourceId(3, -1)));
 		// Report
 		navDrawerItems.add(new NavDrawerItem(mMenuTitle[4], navMenuIcons.getResourceId(4, -1)));
 		// Logs
 		navDrawerItems.add(new NavDrawerItem(mMenuTitle[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
 		// Backup
 		navDrawerItems.add(new NavDrawerItem(mMenuTitle[6], navMenuIcons.getResourceId(6, -1)));
 		//Setting
 		navDrawerItems.add(new NavDrawerItem(mMenuTitle[7], navMenuIcons.getResourceId(7, -1)));
        
		// Recycle the typed array
		navMenuIcons.recycle();
		
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        
        // set up the drawer's list view with items and click listener
        adapter = new NavigationDrawerAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setSelector(R.drawable.list_select);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_actionbar));

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
        if (savedInstanceState == null) {
        	displayView(0);
        }
	}
	
	@Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
	
	private class SlideMenuClickListener implements
		ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
		long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}
	
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new Home();
			break;
		case 1:
			fragment = new StartWorkOut();
			break;
		case 2:
			fragment = new FindAPlan();
			break;
		case 3:
			fragment = new ExerciseLibrary();
			break;
		case 4:
			fragment = new Report();
			break;
		case 5:
			fragment = new Logs();
			break;
		case 6:
			fragment = new Backup();
			break;
		case 7:
			fragment = new Setting();
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(mMenuTitle[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }
	
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
