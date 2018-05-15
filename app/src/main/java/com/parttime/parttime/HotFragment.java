package com.parttime.parttime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Hera Prasetia
 */

public class HotFragment extends Fragment {

    private Button buttonBarista, buttonShoes, buttonBurjo;
    private TextView textViewBarista, textViewShoes, textViewBurjo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_hot, container, false);

        //inisialisasi
        textViewBarista = (TextView) rootView.findViewById(R.id.lihat_barista);
        textViewShoes = (TextView) rootView.findViewById(R.id.lihat_shoes);
        textViewBurjo = (TextView) rootView.findViewById(R.id.lihat_burjo);
        buttonBarista = (Button)rootView.findViewById(R.id.button_barista);
        buttonShoes = (Button)rootView.findViewById(R.id.button_shoes);
        buttonBurjo = (Button)rootView.findViewById(R.id.button_burjo);

        //Text view diklik, lari ke activity
        textViewBarista.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(), "Detail Barista", Toast.LENGTH_LONG).show();
            }
        });
        textViewShoes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(), "Detail Shoes", Toast.LENGTH_LONG).show();
            }
        });
        textViewBurjo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(), "Detail Burjo", Toast.LENGTH_LONG).show();
            }
        });

        //Dialog box per button
        buttonBarista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Sukses apply pekerjaan!");
                builder.setMessage("Silahkan menunggu respon dari pemilik usaha");
                builder.setCancelable(true);

                final AlertDialog closedialog= builder.create();

                closedialog.show();

                final Timer timer2 = new Timer();
                timer2.schedule(new TimerTask() {
                    public void run() {
                        closedialog.dismiss();
                        timer2.cancel(); //this will cancel the timer of the system
                    }
                }, 1500); // the timer will count 5 seconds....
            }
        });
        buttonShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Sukses apply pekerjaan!");
                builder.setMessage("Silahkan menunggu respon dari pemilik usaha");
                builder.setCancelable(true);

                final AlertDialog closedialog= builder.create();

                closedialog.show();

                final Timer timer2 = new Timer();
                timer2.schedule(new TimerTask() {
                    public void run() {
                        closedialog.dismiss();
                        timer2.cancel(); //this will cancel the timer of the system
                    }
                }, 1500); // the timer will count 5 seconds....
            }
        });
        buttonBurjo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Sukses apply pekerjaan!");
                builder.setMessage("Silahkan menunggu respon dari pemilik usaha");
                builder.setCancelable(true);

                final AlertDialog closedialog= builder.create();

                closedialog.show();

                final Timer timer2 = new Timer();
                timer2.schedule(new TimerTask() {
                    public void run() {
                        closedialog.dismiss();
                        timer2.cancel(); //this will cancel the timer of the system
                    }
                }, 1500); // the timer will count 5 seconds....
            }
        });

        return rootView;
    }
}
