package com.my.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private String userName;
    private String password;
    private SharedPreferences.Editor editor;

    private SharedPreferences preferences;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        //Declare SharedPreference
        preferences = getSharedPreferences("user", MODE_PRIVATE);

        // Declare Buttons
        final Button btnLogin = findViewById(R.id.btn_login);
        final Button btnRegister = findViewById(R.id.btn_register);

        //Declare EditText
        final TextInputEditText editTextUserName = findViewById(R.id.username);
        final TextInputEditText editTextPassword = findViewById(R.id.password);

        //Declare Check Box
        final CheckBox checkBoxRemember = findViewById(R.id.checkbox_remember);

        //Save User Remember Change
        checkBoxRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor = preferences.edit();
                editor.putBoolean("rememberState", isChecked);
                editor.apply();
            }
        });

        checkBoxRemember.setChecked(preferences.getBoolean("rememberState",false));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = editTextUserName.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                if (userName.isEmpty()) {
                    editTextUserName.setError("*");
                    editTextUserName.requestFocus();
                }

                if (password.isEmpty() || userName.isEmpty()) {
                    if (!userName.isEmpty())
                        editTextPassword.requestFocus();

                    editTextPassword.setError("");
                    Snackbar.make(findViewById(android.R.id.content), "username or password required", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(userName,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if (checkBoxRemember.isChecked()) {
                            editor = preferences.edit();
                            editor.putString("username", userName);
                            editor.apply();
                        }

                        Toast.makeText(LoginActivity.this, "Welcome  " + userName, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                //Keep Username in Shared Preference If User Check Remember





            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Set Username Value If User Check Remember
        if (!preferences.getString("username", "").isEmpty())
            editTextUserName.setText(preferences.getString("username", ""));
    }
}