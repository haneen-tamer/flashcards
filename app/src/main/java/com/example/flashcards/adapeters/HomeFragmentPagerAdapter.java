package com.example.flashcards.adapeters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.flashcards.fragments.DecksFragment;
import com.example.flashcards.fragments.FoldersFragment;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {


    public HomeFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new DecksFragment();
            case 1:
                return new FoldersFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "My Decks";
            case 1:
                return "My Folders";
        }
        return super.getPageTitle(position);
    }
}
