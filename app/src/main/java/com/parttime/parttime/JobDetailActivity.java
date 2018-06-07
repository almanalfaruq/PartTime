package com.parttime.parttime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

/**
 * Created by danasw on 17/05/2018.
 */

public class JobDetailActivity extends AppCompatActivity {

    private TextView txtJob, txtSalary, txtScheduleTime, txtPlace, txtDescription;
    private Button btnApply;

    private FirebaseFirestore fFirestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        fFirestore = FirebaseFirestore.getInstance();

        String documentId = getIntent().getStringExtra("document_id");

        txtJob = findViewById(R.id.text_job);
        txtSalary = findViewById(R.id.text_salary);
        txtScheduleTime = findViewById(R.id.text_schedule_time);
        txtPlace = findViewById(R.id.text_place);
        txtDescription = findViewById(R.id.text_description);

        btnApply = findViewById(R.id.button_apply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(JobDetailActivity.this, "Anda telah mengapply pekerjaan ini", Toast.LENGTH_SHORT).show();
            }
        });

        fFirestore.collection("jobs").document(documentId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Toast.makeText(JobDetailActivity.this, "Pekerjaan Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }

                Job job = documentSnapshot.toObject(Job.class);
                txtJob.setText(job.getJob() + " " + job.getPlace());
                txtSalary.setText(job.getSalary());
                txtScheduleTime.setText(job.getSchdule() + " " + job.getTime());
                txtPlace.setText(job.getPlace());
                txtDescription.setText(job.getDescription());
            }
        });

    }
}
