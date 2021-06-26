package com.redfeet.movieapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.preference.PreferenceManager;

import com.redfeet.movieapp.databinding.FragmentMovieBinding;

import java.util.List;

public class MovieFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private FragmentMovieBinding binding;
    private String tag;
    private boolean displayedMovies = false;


    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false);
        tag = getTag();
        PreferenceManager.getDefaultSharedPreferences(requireContext()).registerOnSharedPreferenceChangeListener(this);
        displayMovies();
        return binding.getRoot();
    }

    private void displayMovies() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String apikey = sharedPreferences.getString(getString(R.string.pref_key_api_key),null);
        if(TextUtils.isEmpty(tag) && !TextUtils.isEmpty(apikey)){
            switch (tag){
                case MainActivity.FRAG_TAG_POPULAR:
                    TMDbClient.getInstance().getPopularMovies(apikey).observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
                        @Override
                        public void onChanged(List<Movie> movies) {
                            if(movies != null){
                                displayedMovies = true;
                                binding.groupMessage.setVisibility(View.GONE);
                                MovieAdapter adapter = new MovieAdapter(movies);
                                binding.rvMovie.setAdapter(adapter);
                            }
                        }
                    });
                    break;
                case MainActivity.FRAG_TAG_TOP_RATED:
                    TMDbClient.getInstance().getPopularMovies(apikey).observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
                        @Override
                        public void onChanged(List<Movie> movies) {
                            if(movies != null){
                                displayedMovies = true;
                                binding.groupMessage.setVisibility(View.GONE);
                                MovieAdapter adapter = new MovieAdapter(movies);
                                binding.rvMovie.setAdapter(adapter);
                            }
                        }
                    });
                    break;

            }
        }
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(!displayedMovies){
            displayMovies();
        }
    }
}