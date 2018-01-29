package com.wathis.listecourse.controller;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wathis.listecourse.R;
import com.wathis.listecourse.views.ConsulterFragment;
import com.wathis.listecourse.views.NouvelleNoteFragment;
import com.wathis.listecourse.views.ReglageFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitleTextColor(Color.WHITE);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_bar_navigation);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });
    }

    private Fragment olderFragment;
    protected void selectFragment(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment frag = null;
        switch (item.getItemId()) {
            case R.id.action_nouvelle :
                frag = NouvelleNoteFragment.newInstance();
                break;
            case R.id.action_consulter :
                frag = ConsulterFragment.newInstance();
                break;
            case R.id.action_reglages :
                frag = ReglageFragment.newInstance();
                break;
        }
        if (olderFragment != null) {
            fragmentTransaction.remove(olderFragment);
        }
        olderFragment = frag;
        fragmentTransaction.add(R.id.fragment_container, frag);
        fragmentTransaction.commit();
    }
}
