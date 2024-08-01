package com.example.photogalleryapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter class for managing and displaying photo gallery items in a RecyclerView.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context; // Context for inflating views and starting activities
    private List<DataClass> dataList; // List of data items to display

    /**
     * Constructor for initializing adapter with context and data list.
     *
     * @param context  the context from which the adapter is created
     * @param dataList the list of data items to be displayed
     */
    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item view and create the ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Get the current item from the data list
        DataClass dataItem = dataList.get(position);
        
        // Load image using Glide
        Glide.with(context).load(dataItem.getDataImage()).into(holder.recImage);
        
        // Set text views with data from the current item
        holder.recTitle.setText(dataItem.getDataTitle());
        holder.recDesc.setText(dataItem.getDataDesc());
        holder.recLang.setText(dataItem.getDataLang());

        // Set up click listener for the CardView
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to start DetailActivity
                Intent intent = new Intent(context, DetailActivity.class);
                
                // Pass data to DetailActivity using intent extras
                intent.putExtra("Image", dataItem.getDataImage());
                intent.putExtra("Description", dataItem.getDataDesc());
                intent.putExtra("Title", dataItem.getDataTitle());
                intent.putExtra("Key", dataItem.getKey());
                intent.putExtra("Language", dataItem.getDataLang());
                
                // Start DetailActivity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the list
        return dataList.size();
    }

    /**
     * Update the data list and notify the adapter of the changes.
     *
     * @param searchList the new list of data items
     */
    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged(); // Refresh the RecyclerView
    }
}

/**
 * ViewHolder class for holding and recycling views in the RecyclerView.
 */
class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView recImage; // Image view for displaying the image
    TextView recTitle;  // Text view for displaying the title
    TextView recDesc;   // Text view for displaying the description
    TextView recLang;   // Text view for displaying the language
    CardView recCard;   // CardView to handle item clicks

    /**
     * Constructor for initializing view holder with item view.
     *
     * @param itemView the view for this item
     */
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        // Initialize views by finding them in the item layout
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
        recLang = itemView.findViewById(R.id.recPriority);
        recTitle = itemView.findViewById(R.id.recTitle);
    }
}
