package com.parttime.parttime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by hp on 2/10/2018.
 */
public class KategoriFragment extends Fragment {
    private LinearLayout linearFood, linearCleaning, linearTeacher, linearTransportation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_kategori, container, false);

        //inisialisasi
        linearFood = (LinearLayout) rootView.findViewById(R.id.kategori_food);
        linearCleaning = (LinearLayout) rootView.findViewById(R.id.kategori_cleaning);
        linearTeacher = (LinearLayout) rootView.findViewById(R.id.kategori_teacher);
        linearTransportation = (LinearLayout) rootView.findViewById(R.id.kategori_transportation);

        //layout di klik pindah activity
        linearTeacher.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO Teacher Linear Layout on click listener
            }
        });
        linearFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO Food Linear Layout on click listener
            }
        });
        linearCleaning.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO Cleaning Linear Layout on click listener
            }
        });
        linearTransportation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO Transportation Linear Layout on click listener
            }
        });
        return rootView;
    }
}