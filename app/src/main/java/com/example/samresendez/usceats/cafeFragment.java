package com.example.samresendez.usceats;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link cafeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cafeFragment extends Fragment {




    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "cafeName";

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
        if (getArguments() != null) {
            cafeName = getArguments().getString(ARG_PARAM1);
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
        TextView cafeTextView = (TextView) getView().findViewById(R.id.cafeNameTextView);
        cafeTextView.setText(cafeName);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
