package com.example.tugas8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String FINALNAME = "filename.txt";
    Button btnBuat,btnLihat,btnUbah,btnHapus ;
    TextView tvBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnBuat = findViewById(R.id.buat);
        btnUbah = findViewById(R.id.ubah);
        btnLihat = findViewById(R.id.lihat);
        btnHapus = findViewById(R.id.hapus);
        tvBaca = findViewById(R.id.text_baca);

        btnBuat.setOnClickListener(this);
        btnUbah.setOnClickListener(this);
        btnLihat.setOnClickListener(this);
        btnHapus.setOnClickListener(this);


    }
    void buatFile(){
        String isiFile = "Isi File!";
        File file = new File(getFilesDir(), FINALNAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void ubahFile(){
        String ubah = "Update Isi Data File Text";
        File file = new File(getFilesDir(), FINALNAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void bacaFile(){
        File file = new File(getFilesDir(), FINALNAME);
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line !=null){
                    text.append(line);
                    line = br.readLine();
                }
            } catch (IOException e) {
                System.out.println("Error : " + e.getMessage());

            }
            tvBaca.setText(text.toString());
        }
    }
    void hapusFile(){
        File file = new File(getFilesDir(), FINALNAME);
        if (file.exists()){
            file.delete();
        }
    }

    @Override
    public void onClick(View view) {
        run(view.getId());

    }

    private void run(int id) {
        switch (id){
            case R.id.buat:
                buatFile();
                break;
            case R.id.lihat:
                bacaFile();
                break;
            case R.id.ubah:
                ubahFile();
                break;
            case R.id.hapus:
                hapusFile();
                break;
        }
    }
}