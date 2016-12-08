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




    ArrayList<String> dataSet = new ArrayList<>();

    public static class menuViewHolder extends RecyclerView.ViewHolder {

        public TextView menuNameView;

        menuViewHolder(View itemView) {
            super(itemView);
            menuNameView = (TextView) itemView.findViewById(R.id.menuItemName);

        }



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        menuViewHolder relItem = (menuViewHolder) holder;
        relItem.menuNameView.setText(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        if(dataSet == null) {
            return 0;
        }
        return dataSet.size();
    }

    @Override
    public menuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menuitem,parent,false);

        menuViewHolder holder = new menuViewHolder(v);

        return holder;
    }
}
