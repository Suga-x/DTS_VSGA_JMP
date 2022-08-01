package com.example.tugas5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.inputnama:
                Intent moveInputData = new Intent(MainActivity.this, inputnamaActivity.class);
                Toast.makeText(MainActivity.this, "Move to input nama", Toast.LENGTH_SHORT).show();
                startActivity(moveInputData);
                return true;
            case R.id.kalkulator:
                Intent moveKalkulator = new Intent(MainActivity.this, KalkulatorActivity.class);
                Toast.makeText(MainActivity.this, "Move to calculator", Toast.LENGTH_SHORT).show();
                startActivity(moveKalkulator);
                return true;
            case R.id.lv_negara:
                Intent moveLvNegara = new Intent(MainActivity.this, ListViewNegara.class);
                Toast.makeText(MainActivity.this, "Move to listview negara", Toast.LENGTH_SHORT).show();
                startActivity(moveLvNegara);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}