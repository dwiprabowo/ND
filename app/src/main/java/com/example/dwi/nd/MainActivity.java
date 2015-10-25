package com.example.dwi.nd;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private final ThreadLocal<ArrayAdapter<String>> mAdapter = new ThreadLocal<>();

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        mDrawerList = (ListView) findViewById(R.id.navList);
        addDrawerItems();

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                        MainActivity.this
                        , "Menu nomor-" + position + " dipilih!"
                        , Toast.LENGTH_SHORT
                ).show();
            }
        });

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        setupDrawer();

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(
                this
                , mDrawerLayout
                , R.string.drawer_open
                , R.string.drawer_close
        ){
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                if(getSupportActionBar() != null){
                    getSupportActionBar().setTitle("Menunya");
                }
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                if(getSupportActionBar() != null){
                    getSupportActionBar().setTitle(mActivityTitle);
                }
                invalidateOptionsMenu();
            }
        };
    }

    private void addDrawerItems(){
        String[] osArray = {"Ayam Goreng", "Sate", "Rendang", "Bakso", "Gorengan"};
        mAdapter.set(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray));
        mDrawerList.setAdapter(mAdapter.get());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return
                id == R.id.action_settings
                        || mDrawerToggle.onOptionsItemSelected(item)
                        || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
