package com.redfeet.movieapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.redfeet.movieapp.databinding.ActivityMainBinding;

// dependencies sari 1.7.45 pe h module 9 me technophilia ke
public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener{

    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    public static final String FRAG_TAG_POPULAR = "frag-popular";
    public static final String FRAG_TAG_TOP_RATED = "frag-top-rated";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);

        fragmentManager =getSupportFragmentManager();
        loadPopularMoviesFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_popular:
                loadPopularMoviesFragment();
                return true;
            case R.id.action_top_rated:
                loadTopRatedMoviesFragment();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings",Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void loadPopularMoviesFragment(){
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_POPULAR);
//        if fragment already exists then show the fragment
        if(fragment != null){
            fragmentManager.beginTransaction().show(fragment).commit();
        }
        else{
//            if fragment doesn't exists, then create and add the fragment
            fragmentManager.beginTransaction().add(R.id.fragment_container, new MovieFragment(), FRAG_TAG_POPULAR).commit();
        }
//        hide rest of the fragment
        hideFragment(FRAG_TAG_TOP_RATED);
    }

    private void loadTopRatedMoviesFragment(){
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_POPULAR);

//        if fragment already exists then show the fragment
        if(fragment != null){
            fragmentManager.beginTransaction().show(fragment).commit();
            Toast.makeText(this,"toprated",Toast.LENGTH_LONG).show();
        }
        else{
//            if fragment doesn't exists, then create and add the fragment
            fragmentManager.beginTransaction().add(R.id.fragment_container, new MovieFragment(), FRAG_TAG_TOP_RATED).commit();
            Toast.makeText(this,"toprated_else",Toast.LENGTH_LONG).show();
        }
        hideFragment(FRAG_TAG_POPULAR);
    }

    private void hideFragment(String... tags){
        for(String tag:tags){
            fragment = fragmentManager.findFragmentByTag(tag);

            if(fragment != null) {
                fragmentManager.beginTransaction().hide(fragment).commit();
            }
        }
    }
}
