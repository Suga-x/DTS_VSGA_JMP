package com.example.tugaspert9a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername,etPassword,etAlamat,etEmail,etNamaLengkap,etAsalSekolah;
    Button btnSimpan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setTitle("Register");

        etUsername = findViewById(R.id.et_r_username);
        etPassword = findViewById(R.id.et_r_password);
        etAlamat = findViewById(R.id.et_alamat);
        etEmail = findViewById(R.id.et_email);
        etNamaLengkap = findViewById(R.id.et_namaLengkap);
        etAsalSekolah = findViewById(R.id.et_asalSeklolah);

        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidation()){
                    simpanFileData();
                }else{
                    Toast.makeText(RegisterActivity.this, "Mohon lengkapi data !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public boolean isValidation(){
        if (etUsername.getText().equals("") ||etPassword.getText().equals("") ||etAlamat.getText().equals("") ||etAsalSekolah.getText().equals("") ||etEmail.getText().equals("") ||etNamaLengkap.getText().equals("")){
            return false;
        }else{
            return true;
        }
    }
    public void simpanFileData(){
        String isiFile = etUsername.getText().toString() +";"
                + etPassword.getText().toString() +";"
                + etEmail.getText().toString() +";"
                + etNamaLengkap.getText().toString() +";"
                + etAsalSekolah.getText().toString() +";"
                + etAlamat.getText().toString() +";";
        File file = new File(getFilesDir(),etUsername.getText().toString());
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Berhasil !", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}