package com.example.tugas5;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class KalkulatorActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etAngka1, etAngka2;
//    private Button tambah,kurang,kali,bagi,samadengan;
    private TextView hasilnya;
    private double hasiltmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Kalkulator Sederhana");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        etAngka1 = findViewById(R.id.et_angka1);
        etAngka2 = findViewById(R.id.et_angka2);
        hasilnya = findViewById(R.id.hasil);
        Button tambah = findViewById(R.id.btn_tambah);
        tambah.setOnClickListener(this);
        Button kurang = findViewById(R.id.btn_kurang);
        kurang.setOnClickListener(this);
        Button kali = findViewById(R.id.btn_kali);
        kali.setOnClickListener(this);
        Button bagi = findViewById(R.id.btn_bagi);
        bagi.setOnClickListener(this);
        Button samadengan = findViewById(R.id.btn_samadengan);
        samadengan.setOnClickListener(this);

        tambah.setOnClickListener(v -> {
            double a = Double.parseDouble(etAngka1.getText().toString());
            double b = Double.parseDouble(etAngka2.getText().toString());
            hasiltmp = a +b;
        });
        kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a = Double.parseDouble(etAngka1.getText().toString());
                double b = Double.parseDouble(etAngka2.getText().toString());
                hasiltmp = a -b;
            }
        });
        kali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a = Double.parseDouble(etAngka1.getText().toString());
                double b = Double.parseDouble(etAngka2.getText().toString());
                hasiltmp = a *b;
            }
        });
        bagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a = Double.parseDouble(etAngka1.getText().toString());
                double b = Double.parseDouble(etAngka2.getText().toString());
                hasiltmp = a /b;
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_samadengan){
            boolean isEmpty = false;
            if (TextUtils.isEmpty(etAngka1.getText().toString())){
                isEmpty = true;
                etAngka1.setError("Field ini tak boleh kosong !");
            }
            if (TextUtils.isEmpty(etAngka2.getText().toString())){
                isEmpty = true;
                etAngka2.setError("Field ini tak boleh kosong !");
            }
            if (!isEmpty){
                hasilnya.setText(String.valueOf(hasiltmp));
            }
        }
    }
}