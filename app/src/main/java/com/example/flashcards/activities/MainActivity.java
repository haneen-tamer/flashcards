package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.flashcards.R;
import com.example.flashcards.adapeters.HomeFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.mainViewPager);
        viewPager.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager()));

        TabLayout tl = findViewById(R.id.mainTabLayout);
        tl.setupWithViewPager(viewPager);
    }
}