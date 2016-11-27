package com.example.samresendez.usceats;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SamResendez on 11/3/16.
 */

public class menuAdapter extends RecyclerView.Adapter {

    ArrayList<menuItem> dataSet = new ArrayList<>();

    public static class menuViewHolder extends RecyclerView.ViewHolder {

        public TextView menuNameView;

        menuViewHolder(View itemView) {
            super(itemView);
            menuNameView = (TextView) itemView.findViewById(R.id.menuTextView);

        }



    }

    @Override
    public void onBindViewHolder(menuViewHolder holder, int position) {
        menuItem item = dataSet.get(position);
        holder.menuNameView.setText(item.itemName);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public menuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(,parent,false);


        return null;
    }
}
