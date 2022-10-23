package com.example.tugas10_liblary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etInputName;
    Button btnStore,btnGetAll;
    TextView tvName;
    DatabaseHelper dbHelper;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
        tvName = findViewById(R.id.tv_Name);
        etInputName = findViewById(R.id.et_name);
        btnStore = findViewById(R.id.btn_store);
        btnGetAll = findViewById(R.id.btn_getAll);

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addStudentDetail(etInputName.getText().toString());
                etInputName.setText("");
                Toast.makeText(MainActivity.this, "Store Data Sucess", Toast.LENGTH_SHORT).show();
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList = dbHelper.getAllStudentList();
                tvName.setText("");
                for (String item:arrayList){
                    tvName.append(item+ " ");
                }
            }
        });
    }
}