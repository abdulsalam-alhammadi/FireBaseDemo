package com.my.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private String email;
    private String password;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TextInputEditText editTextEmail = findViewById(R.id.email);
        final TextInputEditText editTextPassword = findViewById(R.id.password);
        final Button btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth =FirebaseAuth.getInstance();

                email = editTextEmail.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                if(email.isEmpty()){
                    editTextEmail.setError("*");
                    editTextEmail.requestFocus();
                }

                if(password.isEmpty() || email.isEmpty()){
                    if(!email.isEmpty())
                        editTextPassword.requestFocus();

                    editTextPassword.setError("*");
                    return;
                }

                registerUser();
            }
        });
    }

    private void registerUser() {
            ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait ...");
        progressDialog.show();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    finish();
                }


            }
        }).addOnFailureListener(RegisterActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}