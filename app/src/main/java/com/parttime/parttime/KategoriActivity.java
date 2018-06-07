package com.parttime.parttime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class KategoriActivity extends AppCompatActivity {

    private RecyclerView recJob;
    private RecyclerView.Adapter adapter;
    private List<Job> jobs = new ArrayList<>();
    private FirebaseFirestore fFirestore;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        toolbar = findViewById(R.id.toolbar);
        recJob = findViewById(R.id.recycler_job);

        String kategori = getIntent().getStringExtra("kategori");

        setView(kategori);
    }

    private void setView(String kategori) {
        switch (kategori) {
            case "Pengajar":
                toolbar.setTitle("Kategori Pengajar");
                break;
            case "Transportasi":
                toolbar.setTitle("Kategori Transportasi");
                break;
            case "Tempat Makan":
                toolbar.setTitle("Kategori Tempat Makan");
                break;
            case "Kebersihan":
                toolbar.setTitle("Kategori Kebersihan");
                break;
            default:
                toolbar.setTitle("Kategori");

        }
        setRecyclerContent(kategori);
    }

    private void setRecyclerContent(String kategori) {
        fFirestore = FirebaseFirestore.getInstance();

        recJob.setLayoutManager(new LinearLayoutManager(this));
        adapter = new JobAdapter(jobs, this);
        recJob.setAdapter(adapter);

        fFirestore.collection("jobs")
                .whereEqualTo("category", kategori)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot querySnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                        jobs.clear();
                        for (DocumentSnapshot doc : querySnapshot) {
                            Job job = doc.toObject(Job.class);
                            job.setId(doc.getId());
                            jobs.add(job);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
