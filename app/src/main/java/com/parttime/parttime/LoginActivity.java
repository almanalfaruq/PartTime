package com.parttime.parttime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by almantera on 06/04/18.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private TextInputLayout mTxtEmail, mTxtPassword;
    private Button mBtnLogin;
    private TextView mTxtSignup;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initFirebase();
        initWidget();
    }

    private void initFirebase() {
        fAuth = FirebaseAuth.getInstance();
    }

    private void initWidget() {
        mTxtEmail = findViewById(R.id.input_email);
        mTxtPassword = findViewById(R.id.input_password);
        mBtnLogin = findViewById(R.id.button_login);
        mTxtSignup = findViewById(R.id.link_signup);
        mBtnLogin.setOnClickListener(this);
        mTxtSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                authenticateUser();
                break;
            case R.id.link_signup:
                startActivity(SignupActivity.class);
                break;
        }
    }

    private void authenticateUser() {
        String email = mTxtEmail.getEditText().getText().toString();
        String password = mTxtPassword.getEditText().getText().toString();
        if (isEmailAndPasswordEmpty(email, password)) {
            Toast.makeText(this, "Harap lengkapi data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();
            fAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            // Starting main activity
                            startActivity(MainActivity.class);
                            Toast.makeText(LoginActivity.this,
                                    "Login success", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this,
                                    "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, e.getMessage());
                        }
                    });
        }
    }

    private boolean isEmailAndPasswordEmpty(String email, String password) {
        if (email == null || email.isEmpty()) {
            mTxtEmail.setErrorEnabled(true);
            mTxtEmail.setError("Harap isi email terlebih dahulu");
            mTxtPassword.setErrorEnabled(false);
            mTxtPassword.setError(null);
            return true;
        } else if (password == null || password.isEmpty()) {
            mTxtPassword.setErrorEnabled(true);
            mTxtPassword.setError("Harap isi password terlebih dahulu");
            mTxtEmail.setErrorEnabled(false);
            mTxtEmail.setError(null);
            return true;
        }
        return false;
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(LoginActivity.this, cls);
        startActivity(intent);
    }
}
