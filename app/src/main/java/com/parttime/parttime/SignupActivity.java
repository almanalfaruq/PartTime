package com.parttime.parttime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by almantera on 06/04/18.
 */

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignup;
    private TextView txtLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnSignup = (Button) findViewById(R.id.button_signup);
        txtLogin = (TextView) findViewById(R.id.link_login);
        btnSignup.setOnClickListener(this);
        txtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_signup:
                Toast.makeText(this, "Register success!", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;
            case R.id.link_login:
                onBackPressed();
                break;
        }
    }
}
