package com.example.photogalleryapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass that displays information about the app.
 */
public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // This method is called to create the view hierarchy associated with the fragment.
        // The layout file fragment_about.xml is inflated and returned as the view for this fragment.
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}
