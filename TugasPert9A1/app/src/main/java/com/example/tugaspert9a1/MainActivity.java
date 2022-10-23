package com.example.tugaspert9a1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "login";

    EditText etUsername, etPassword, etEmail, etNama, etAsalSekolah, etAlamat;
    TextView tvUsername, tvPassword,tvEmail,tvNama,tvAsalSekolah,tvAlamat;
    Button btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setTitle("Main Page");

        etUsername = findViewById(R.id.et_r_username);
        etPassword = findViewById(R.id.et_r_password);
        etEmail = findViewById(R.id.et_email);
        etNama = findViewById(R.id.et_namaLengkap);
        etAsalSekolah = findViewById(R.id.et_asalSeklolah);
        etAlamat = findViewById(R.id.et_alamat);

        tvUsername = findViewById(R.id.tv_r_uname);
        tvPassword = findViewById(R.id.tv_r_password);
        tvEmail = findViewById(R.id.tv_email);
        tvNama = findViewById(R.id.tv_namaLengkap);
        tvAsalSekolah = findViewById(R.id.tv_asalSekolah);
        tvAlamat = findViewById(R.id.tv_alamat);

        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setVisibility(View.GONE);

        etUsername.setEnabled(false);
        etPassword.setVisibility(View.GONE);
        tvPassword.setVisibility(View.GONE);
        etEmail.setEnabled(false);
        etNama.setEnabled(false);
        etAsalSekolah.setEnabled(false);
        etAlamat.setEnabled(false);

        BacaFileLogin();

    }
    void BacaFileLogin(){
        File file = new File(getFilesDir(), FILENAME);

        if(file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }

            String data = text.toString();
            String[] dataUser = data.split(";");

            bacaDataUser(dataUser[0]);
        }
    }

    void bacaDataUser(String namaFile){
        File file = new File(getFilesDir(), namaFile);

        if(file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }

            String data = text.toString();
            String[] dataUser = data.split(";");

            //Set data berdasarkan file
            etUsername.setText(dataUser[0]);
            etEmail.setText(dataUser[2]);
            etNama.setText(dataUser[3]);
            etAsalSekolah.setText(dataUser[4]);
            etAlamat.setText(dataUser[5]);
        }else{
            Toast.makeText(this, "User tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memu, menu);
        return true;
    }

    //action ketika memilih menu logout

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                //pastikan user memang ingin keluar
                tampilkanDialogKonfirmasiLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //fungsi hapus file
    void hapusFile(){
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists()){
            file.delete();
        }
    }
    void tampilkanDialogKonfirmasiLogout(){
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin akan logout?")
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        hapusFile();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
}