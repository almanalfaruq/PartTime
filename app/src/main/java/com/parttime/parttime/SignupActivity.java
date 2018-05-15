package com.parttime.parttime;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by almantera on 06/04/18.
 */

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = SignupActivity.class.getSimpleName();

    private Button mBtnSignup, mBtnChooseFile;
    private TextView mTxtLogin;
    private TextInputLayout mTxtName, mTxtTtl,
            mTxtAddress, mTxtEmail, mTxtPassword;
    private EditText mTxtFilePath;

    private FirebaseAuth fAuth;
    private FirebaseFirestore fFirestore;
    private FirebaseStorage fStorage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initFirebase();
        initWidget();
    }

    private void initFirebase() {
        fAuth = FirebaseAuth.getInstance();
        fFirestore = FirebaseFirestore.getInstance();
        fStorage = FirebaseStorage.getInstance();
    }

    private void initWidget() {
        mBtnSignup = findViewById(R.id.button_signup);
        mBtnChooseFile = findViewById(R.id.button_choose_file);
        mTxtLogin = findViewById(R.id.link_login);
        mTxtName = findViewById(R.id.input_name);
        mTxtTtl = findViewById(R.id.input_ttl);
        mTxtAddress = findViewById(R.id.input_address);
        mTxtEmail = findViewById(R.id.input_email);
        mTxtPassword = findViewById(R.id.input_password);
        mTxtFilePath = findViewById(R.id.text_file_path);
        mBtnSignup.setOnClickListener(this);
        mTxtLogin.setOnClickListener(this);
        mBtnChooseFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_signup:
                registerNewUser();
                break;
            case R.id.link_login:
                onBackPressed();
                break;
            case R.id.button_choose_file:
                checkPermissionsAndOpenFilePicker();
                break;
        }
    }

    private void registerNewUser() {
        String email = mTxtEmail.getEditText().getText().toString();
        String password = mTxtPassword.getEditText().getText().toString();
        fAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();
                    createUserInfo(user.getUid());
                } else {
                    Toast.makeText(SignupActivity.this,
                            "Tidak bisa mendaftarkan pengguna", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, task.getException().getMessage());
                }
            }
        });
    }

    private void createUserInfo(String userId) {
        String name = mTxtName.getEditText().getText().toString();
        String ttl = mTxtTtl.getEditText().getText().toString();
        String address = mTxtAddress.getEditText().getText().toString();
        User user = new User(userId, name, ttl, address);
        fFirestore.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        if (isFileExists()) {
                            uploadCv();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignupActivity.this,
                                "Tidak bisa mendaftarkan pengguna", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.getMessage());
                    }
                });
    }

    private boolean isFileExists() {
        return !mTxtFilePath.getText().toString().isEmpty();
    }

    private void uploadCv() {
        String filePath = mTxtFilePath.getText().toString();
        try {
            InputStream inputStream = new FileInputStream(new File(filePath));
            UploadTask uploadTask =
                    fStorage.getReference("cv").putStream(inputStream);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(SignupActivity.this,
                            "Register success!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignupActivity.this,
                            "Tidak bisa mendaftarkan pengguna", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, e.getMessage());
                }
            });
        } catch (FileNotFoundException e) {
            Log.d(TAG, e.getMessage());
        }
    }


    private void checkPermissionsAndOpenFilePicker() {
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, 123);
            }
        } else {
            openFilePicker();
        }
    }

    private void showError() {
        Toast.makeText(this, "Allow external storage reading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 123: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFilePicker();
                } else {
                    showError();
                }
            }
        }
    }

    private void openFilePicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(456)
                .withHiddenFiles(true)
                .withTitle("Pilih CV")
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 456 && resultCode == RESULT_OK) {
            String path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);

            if (path != null) {
                Log.d("Path: ", path);
                mTxtFilePath.setText(path);
            }
        }
    }
}
