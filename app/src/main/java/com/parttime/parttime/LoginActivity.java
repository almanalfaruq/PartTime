package com.parttime.parttime;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private TextView txtSignup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.button_login);
        txtSignup = (TextView) findViewById(R.id.link_signup);
        btnLogin.setOnClickListener(this);
        txtSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();
                startActivity(CategoryActivity.class);
                finish();
                break;
            case R.id.link_signup:
                startActivity(SignupActivity.class);
                break;
        }
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(LoginActivity.this, cls);
        startActivity(intent);
    }
}
