package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.project1.fragment.CurrencyFragment;
import com.example.project1.fragment.HomeFragment;
import com.example.project1.fragment.ProgramFragment;
import com.example.project1.fragment.ScienceFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_SCIENCE = 1;
    private static final int FRAGMENT_PROGRAM = 2;
    private static final int FRAGMENT_CURRENCY = 3;

    private int mCurrentFragment = FRAGMENT_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_standard).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(id == R.id.nav_standard) {
            if(mCurrentFragment != FRAGMENT_HOME) {
                replaceFragment(new HomeFragment());
                mCurrentFragment = FRAGMENT_HOME;
                navigationView.getMenu().findItem(R.id.nav_standard).setChecked(true);
                navigationView.getMenu().findItem(R.id.nav_scientific).setChecked(false);
                navigationView.getMenu().findItem(R.id.nav_programmer).setChecked(false);
                navigationView.getMenu().findItem(R.id.nav_currency).setChecked(false);
            }
        } else if(id == R.id.nav_scientific) {
            if(mCurrentFragment != FRAGMENT_SCIENCE) {
                replaceFragment(new ScienceFragment());
                mCurrentFragment = FRAGMENT_SCIENCE;
                navigationView.getMenu().findItem(R.id.nav_standard).setChecked(false);
                navigationView.getMenu().findItem(R.id.nav_scientific).setChecked(true);
                navigationView.getMenu().findItem(R.id.nav_programmer).setChecked(false);
                navigationView.getMenu().findItem(R.id.nav_currency).setChecked(false);
            }
        } else if(id == R.id.nav_programmer) {
            if(mCurrentFragment != FRAGMENT_PROGRAM) {
                replaceFragment(new ProgramFragment());
                mCurrentFragment = FRAGMENT_PROGRAM;
                navigationView.getMenu().findItem(R.id.nav_standard).setChecked(false);
                navigationView.getMenu().findItem(R.id.nav_scientific).setChecked(false);
                navigationView.getMenu().findItem(R.id.nav_programmer).setChecked(true);
                navigationView.getMenu().findItem(R.id.nav_currency).setChecked(false);
            }
        } else if(id == R.id.nav_currency) {
            if(mCurrentFragment != FRAGMENT_CURRENCY) {
                replaceFragment(new CurrencyFragment());
                mCurrentFragment = FRAGMENT_CURRENCY;
                navigationView.getMenu().findItem(R.id.nav_standard).setChecked(false);
                navigationView.getMenu().findItem(R.id.nav_scientific).setChecked(false);
                navigationView.getMenu().findItem(R.id.nav_programmer).setChecked(false);
                navigationView.getMenu().findItem(R.id.nav_currency).setChecked(true);
            }
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_content, fragment);
        transaction.commit();
    }

}