package com.example.photogalleryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    // UI elements
    FloatingActionButton fab;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize UI elements
        recyclerView = view.findViewById(R.id.recyclerView);
        fab = view.findViewById(R.id.fab);
        searchView = view.findViewById(R.id.search);

        // Setup RecyclerView with GridLayoutManager
        searchView.clearFocus();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Setup and show a loading dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        // Initialize dataList and adapter
        dataList = new ArrayList<>();
        adapter = new MyAdapter(getContext(), dataList);
        recyclerView.setAdapter(adapter);

        // Setup Firebase database reference and listener
        databaseReference = FirebaseDatabase.getInstance().getReference("Android Uploads");
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Clear existing data and populate the list with new data from Firebase
                dataList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                    dataClass.setKey(itemSnapshot.getKey());
                    dataList.add(dataClass);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss(); // Dismiss the loading dialog
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss(); // Dismiss the loading dialog on error
            }
        });

        // Setup SearchView listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // No action on query text submit
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Call searchList method to filter data based on the search query
                searchList(newText);
                return true;
            }
        });

        // Setup FloatingActionButton to open UploadActivity
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UploadActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    // Method to filter data based on the search query
    public void searchList(String text) {
        ArrayList<DataClass> searchList = new ArrayList<>();
        for (DataClass dataClass : dataList) {
            if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);
    }
}
