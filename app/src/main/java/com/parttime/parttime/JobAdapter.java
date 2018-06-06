package com.parttime.parttime;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danasw on 17/05/2018.
 */

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    private List<Job> jobs = new ArrayList<>();
    private Context context;
    public JobAdapter(List<Job> jobs, Context context) {
        this.jobs = jobs;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_job, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Job job = jobs.get(position);
        holder.txtId.setText(job.getId());
        holder.txtJob.setText(job.getJob());
        holder.txtSchedule.setText(job.getSchdule());
        holder.txtTime.setText("");
        holder.txtPlace.setText(job.getPlace());
    }


    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtId, txtJob, txtPlace, txtSchedule, txtTime, txtSeeJob;
        private Button btnApply;
        public ViewHolder(final View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.text_id);
            txtJob = itemView.findViewById(R.id.text_job);
            txtPlace = itemView.findViewById(R.id.text_place);
            txtSchedule = itemView.findViewById(R.id.text_schedule);
            txtTime = itemView.findViewById(R.id.text_time);
            txtSeeJob = itemView.findViewById(R.id.text_see_job);
            btnApply = itemView.findViewById(R.id.button_apply);
            txtSeeJob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, JobDetailActivity.class);
                    intent.putExtra("document_id", txtId.getText().toString());
                    context.startActivity(intent);
                }
            });
            btnApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Anda telah mengapply pekerjaan ini", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
