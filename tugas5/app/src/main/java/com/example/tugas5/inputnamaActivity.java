package com.example.tugas5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class inputnamaActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etnama;
    private TextView tvnama;
    private  static final String STATE_RESULT="state_result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputnama);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Input Nama");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        etnama = findViewById(R.id.et_nama);
        tvnama = findViewById(R.id.tv_nama);
        Button btnhasil = findViewById(R.id.btn_hasil);
        btnhasil.setOnClickListener(this);

        if(savedInstanceState != null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvnama.setText(result);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvnama.getText().toString());
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_hasil){
            boolean isEmpty =false;
            if (TextUtils.isEmpty(etnama.getText().toString())){
                isEmpty = true;
                etnama.setError("Field ini tak boleh kosong !");
            }
            if (!isEmpty){
                tvnama.setText(etnama.getText().toString());
            }
        }
    }
}