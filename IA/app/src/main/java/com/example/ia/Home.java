package com.example.ia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ia.Fragments.Main.MainWorkFragment;
import com.example.ia.Fragments.Others.OtherFragment;
import com.example.ia.Fragments.Personal.PersonalFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // as soon as the application opens the first fragment should be shown to the user
        // in this case it is algorithm fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainWorkFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            // get the selected fragment by using their id.
            Fragment selectedFragment = null;
            switch (item.getItemId())
            {
                case R.id.MainWork:
                    selectedFragment = new MainWorkFragment();
                    break;
                case R.id.Personal:
                    selectedFragment = new PersonalFragment();
                    break;
                case R.id.Other:
                    selectedFragment = new OtherFragment();
                    break;
            }
            // replace the one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
            return true;
        }
    };
}

