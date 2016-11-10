package com.example.samresendez.usceats;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by SamResendez on 11/3/16.
 */

public class menuAdapter extends RecyclerView.Adapter {

    ArrayList<menuItem> dataSet = new ArrayList<>();

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return null;
    }
}
