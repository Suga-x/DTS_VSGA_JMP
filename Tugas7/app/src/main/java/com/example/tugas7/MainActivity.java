package com.example.tugas7;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login Page");

        TextInputLayout etUsername = findViewById(R.id.et_username);
        TextInputLayout etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getEditText().getText().toString();
                String password = etPassword.getEditText().getText().toString();
                if (username.equals("AgusRs_jmp") && password.equals("123345")){
                    finish();
                    Toast.makeText(MainActivity.this, "Berhasil login !", Toast.LENGTH_SHORT).show();
                    Intent pindahLpage = new Intent(MainActivity.this, NegaraActivity.class);
                    startActivity(pindahLpage);
                }else{
                    Toast.makeText(MainActivity.this, "Pastikan Username/Password Sesuai !", Toast.LENGTH_SHORT).show();
                    etUsername.getEditText().setText("");
                    etPassword.getEditText().setText("");
                }
            }
        });
    }
}