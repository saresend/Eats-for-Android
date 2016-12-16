package com.example.samresendez.usceats;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link cafeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cafeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private menuAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "cafeName";

    private ArrayList<ArrayList<String>>  dataSet;
    private String cafeName;

    public cafeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cafeName Parameter 1.
     * @return A new instance of fragment cafeFragment.
     */

    public static cafeFragment newInstance(String cafeName) {
        cafeFragment fragment = new cafeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, cafeName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new menuAdapter();
        dataSet = new ArrayList<>();



        if (getArguments() != null) {
            cafeName = getArguments().getString(ARG_PARAM1);

            retrieveCafeteriaMenuTask task = new retrieveCafeteriaMenuTask();
            task.adapter = this.mAdapter;
            task.cafeName = this.cafeName;
            task.mArrayList = this.dataSet;
            Log.e("Firing: ", "Menu Async Fired");
            task.execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_cafe, container, false);
    }

    @Override //The view is successfully built from this point forward: Place UI stuff here!
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        CardView cardView = (CardView) this.getView().findViewById(R.id.titleCard);
        TextView cardTitle = (TextView) cardView.findViewById(R.id.cardTextView);



        mRecyclerView = (RecyclerView) getView().findViewById(R.id.menuListRecyclerView);

        cardTitle.setText(cafeName);
        if(cafeName == "EVK") {
            cardView.setBackground(getContext().getDrawable(R.mipmap.evkimage));
            this.getView().setBackgroundColor(Color.parseColor("#4CAF50"));

            mLayoutManager = new LinearLayoutManager(getView().getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

            mLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

            //mAdapter.dataSet = dataSet.get(0);
            mRecyclerView.setAdapter(mAdapter);

        }
        else if(cafeName == "Parkside") {
            cardView.setBackground(getContext().getDrawable(R.mipmap.parkside));
            this.getView().setBackgroundColor(Color.parseColor("#FF9800"));


            mLayoutManager = new LinearLayoutManager(getView().getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

            mLayoutManager = new LinearLayoutManager(getView().getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

           // mAdapter.dataSet = dataSet.get(1);
            mRecyclerView.setAdapter(mAdapter);
        }
        else if(cafeName == "Cafe 84") {
            cardView.setBackground(getContext().getDrawable(R.mipmap.cafe84));
            this.getView().setBackgroundColor(Color.parseColor("#03A9F4"));

            mLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

            mLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

           // mAdapter.dataSet = dataSet.get(2);
            mRecyclerView.setAdapter(mAdapter);
        }

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
