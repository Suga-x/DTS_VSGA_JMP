package com.example.tugas7;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class NegaraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negara);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ListView Negara");

        ArrayList<String> alNegara =new ArrayList<>();
        String [] negara ={"Indonesia","Malaysia","Singapore","Italy","Inggris","Belanda","Argentina","Chille", "Mesir","Uganda"};
        Collections.addAll(alNegara, negara);

        EditText etInputNegara = findViewById(R.id.et_inputNegara);
        Button btnTambah = findViewById(R.id.btn_subit);
        ListView lvNegara = findViewById(R.id.lv_negara);

        ArrayAdapter<String> lvNegaraAdapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alNegara);
        lvNegara.setAdapter(lvNegaraAdapter);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaNegara = etInputNegara.getText().toString();
                if (!namaNegara.equals("")){
                    etInputNegara.setText("");
                    alNegara.add(namaNegara);
                    lvNegaraAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(NegaraActivity.this, "Input Masih kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}