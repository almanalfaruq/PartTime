package com.parttime.parttime;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;

/**
 * Created by danasw on 17/05/2018.
 */

public class JobAddActivity extends AppCompatActivity {

    private TextInputLayout txtJob, txtPlace, txtSchedule, txtLastEducation,
            txtSalary, txtGender, txtDescription, txtContact;
    private Spinner spCategory;
    private Button btnSave;

    private FirebaseFirestore fFirestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        fFirestore = FirebaseFirestore.getInstance();
        initWidget();
    }

    private void initWidget() {
        txtJob = findViewById(R.id.input_job);
        txtPlace = findViewById(R.id.input_place);
        txtSchedule = findViewById(R.id.input_schdule);
        txtLastEducation = findViewById(R.id.input_last_education);
        txtSalary = findViewById(R.id.input_salary);
        txtGender = findViewById(R.id.input_gender);
        txtDescription = findViewById(R.id.input_desc);
        txtContact = findViewById(R.id.input_contact);
        spCategory = findViewById(R.id.spinner_category);
        btnSave = findViewById(R.id.button_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveJob();
            }
        });
        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.list_category,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);
    }

    private void saveJob() {
        String sJob = txtJob.getEditText().getText().toString();
        String sPlace = txtPlace.getEditText().getText().toString();
        String sSchedule = txtSchedule.getEditText().getText().toString();
        String sLastEdu = txtLastEducation.getEditText().getText().toString();
        String sSalary = txtSalary.getEditText().getText().toString();
        String sGender = txtGender.getEditText().getText().toString();
        String sDesc = txtDescription.getEditText().getText().toString();
        String sContact = txtContact.getEditText().getText().toString();
        String sCategory = spCategory.getSelectedItem().toString();
        Job job = new Job(sJob, sPlace, sSchedule, "", sLastEdu,
                sSalary, sGender, sDesc, sContact, sCategory);
        fFirestore.collection("jobs").add(job)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(JobAddActivity.this, "Berhasil menambahkan pekerjaan", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(JobAddActivity.this, "Gagal menambahkan pekerjaan", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}