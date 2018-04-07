package com.parttime.parttime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by almantera on 06/04/18.
 */

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout llTeacher, llFood, llCleaning, llTransportation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        llTeacher = (LinearLayout) findViewById(R.id.kategori_teacher);
        llFood = (LinearLayout) findViewById(R.id.kategori_food);
        llCleaning = (LinearLayout) findViewById(R.id.kategori_cleaning);
        llTransportation = (LinearLayout) findViewById(R.id.kategori_transportation);
        llTeacher.setOnClickListener(this);
        llFood.setOnClickListener(this);
        llCleaning.setOnClickListener(this);
        llTransportation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kategori_teacher:
                Toast.makeText(this, "Clicked teacher category",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.kategori_food:
                Toast.makeText(this, "Clicked food category",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.kategori_cleaning:
                Toast.makeText(this, "Clicked cleaning category",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.kategori_transportation:
                Toast.makeText(this, "Clicked transportation category",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
