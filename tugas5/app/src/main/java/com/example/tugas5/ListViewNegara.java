package com.example.tugas5;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ListViewNegara extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String [] negara ={"Indonesia","Malaysia","Singapore","Italy","Inggris","Belanda","Argentina","Chille", "Mesir","Uganda"};
    private ArrayList<String> data = new ArrayList<>();
    ListView lv_negara;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_negara);
//        buat edit action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ListView Negara");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        lv_negara = findViewById(R.id.list_negara);
        lv_negara.setOnItemClickListener(this);
        getData();
        ArrayAdapter adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, data);
        lv_negara.setAdapter(adapter);
    }
    private void getData(){
        Collections.addAll(data, negara);
    }

    @Override
    public void onItemClick(AdapterView adapterView, View view, int position, long l) {
        String getNegara = data.get(position);
        Toast.makeText(this, "Negara dipilih: "+ getNegara, Toast.LENGTH_SHORT).show();
    }


}