package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqllite.helper.DbHelper;
import com.rii.app1_sqlite.helper.DBHelper;

public class DetailActivity extends AppCompatActivity {

    private DbHelper.DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        helper = new DbHelper.DBHelper(this);

        EditText etnama = findViewById(R.id.et_nama);
        EditText etAlamat = findViewById(R.id.et_alamat);
        Button tombol = findViewById(R.id.button);

        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etnama.getText().toString();
                String alamat = etAlamat.getText().toString();

                if(nama.length()==0 && alamat.length()==0) {
                    Toast.makeText(DetailActivity.this,
                            "Nama/alamat tidak boleh kosong",
                            Toast.LENGTH_SHORT).show();
                } else {
                    insert(nama, alamat);
                }
            }
        });
    }

    private void insert(String nama, String alamat) {
        helper.insert(nama, alamat);
        Toast.makeText(this, "Data diproses", Toast.LENGTH_SHORT).show();
        finish();
    }
}