package com.horus_vision.agrid.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.horus_vision.agrid.APIConstants;
import com.horus_vision.agrid.Constants;
import com.horus_vision.agrid.DAO;
import com.horus_vision.agrid.DaoHelper;
import com.horus_vision.agrid.MainViewPager.MainViewPagerAdapter;
import com.horus_vision.agrid.R;
import com.horus_vision.agrid.enums.ModelObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private MainViewPagerAdapter mainViewPagerAdapter;

    private DAO myDao;

    private String username;

    private DAO.Role userRole;

    public DaoHelper.UISkeleton getUiSkeleton() {
        return uiSkeleton;
    }

    private DaoHelper.UISkeleton uiSkeleton;

    private List<String> menuItemTitles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDao = APIConstants.getmDAO();
        username = getIntent().getStringExtra(Constants.roleExtra);
        userRole = APIConstants.getRoleFromUsername(username);
        uiSkeleton = DaoHelper.getUISkeletonFromRole(myDao,userRole);
        setupViewPager();
        setupDrawer();
    }

    private void setupViewPager() {
        viewPager = findViewById(R.id.main_viewpager);
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), uiSkeleton);
        viewPager.setAdapter(mainViewPagerAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mainViewPagerAdapter.SetupFragments(getApplicationContext());
    }

    private void setupDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setupNavigationViewMenu(navigationView.getMenu());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    private void setupNavigationViewMenu(Menu menu) {
        for (int i = 0; i < uiSkeleton.uiCategories.size(); i++) {
            //ModelObject modelObject = ModelObject.values()[i];
            MenuItem menuItem = menu.add(R.id.menu_group, Menu.FIRST + i, i, uiSkeleton.uiCategories.get(i));
            menuItem.setShortcut('3', 'c');
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menuItemTitles.add(menuItem.getTitle().toString());
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        item.setChecked(true);
        //scroll to appropriate fragment
        int index = menuItemTitles.indexOf(item.getTitle());
        if (index >= 0 && index < viewPager.getAdapter().getCount()) {

            viewPager.setCurrentItem(index, true);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
