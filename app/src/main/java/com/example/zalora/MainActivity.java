package com.example.zalora;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zalora.adapter.LeftDrawerListItemAdapter;
import com.example.zalora.utils.Utils;

import java.util.List;

public class MainActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private ListView leftDrawerList;
    private List<LeftDrawerListItem> leftDrawerListItems;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private CharSequence drawerTitle;
    private CharSequence alternateDrawerTitle;
    private int currentItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.retrieveLeftDrawerList(this);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        alternateDrawerTitle = getTitle();
        drawerTitle = getString(R.string.app_name);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftDrawerList = (ListView) findViewById(R.id.left_drawer);
        leftDrawerListItems = ApplicationStatus.getInstance()
                .getLeftDrawerListItems();
        leftDrawerList.setAdapter(new LeftDrawerListItemAdapter(this,
                leftDrawerListItems));
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);
        leftDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(alternateDrawerTitle);
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerTitle);
                supportInvalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            for (int i = 0; i < getSupportFragmentManager()
                    .getBackStackEntryCount(); ++i) {
                getSupportFragmentManager().popBackStack();
            }
        } else {
            super.onBackPressed();
        }
    }

    public boolean isDrawerOpen() {
        return drawerLayout.isDrawerOpen(leftDrawerList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        if (currentItemPosition != position) {
            Bundle args = new Bundle();
            args.putInt("position", position);
            Utils.openNextFragment(this, position, args);
            leftDrawerList.setItemChecked(position, true);
            setTitle(leftDrawerListItems.get(position).getName());
            drawerLayout.closeDrawer(leftDrawerList);
            currentItemPosition = position;
        } else {
            drawerLayout.closeDrawer(leftDrawerList);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        alternateDrawerTitle = title;
        getSupportActionBar().setTitle(alternateDrawerTitle);
    }

}