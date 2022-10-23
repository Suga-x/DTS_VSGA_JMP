package com.example.jmp_certification;




import static android.provider.MediaStore.MediaColumns.TITLE;

import static com.example.jmp_certification.db.DatabaseConstract.NoteColumns.ALAMAT;
import static com.example.jmp_certification.db.DatabaseConstract.NoteColumns.DATE;
import static com.example.jmp_certification.db.DatabaseConstract.NoteColumns.JENISKELAMIN;
import static com.example.jmp_certification.db.DatabaseConstract.NoteColumns.LOKASI;
import static com.example.jmp_certification.db.DatabaseConstract.NoteColumns.NAMA;
import static com.example.jmp_certification.db.DatabaseConstract.NoteColumns.NOHP;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jmp_certification.db.FormNoteHelper;
import com.example.jmp_certification.entity.FormNote;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteAddUpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNama, edtNohp,edtAlamat;
    private RadioGroup list_opsi;
    private RadioButton radioLaki,radioPerempuan;
    private boolean isEdit = false;
    private FormNote formNote;
    private int position;
    public String tmp ="";
    private FormNoteHelper formNoteHelper;

    public static final String EXTRA_NOTE = "extra_note";
    public static final String EXTRA_POSITION = "extra_position";
    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 101;
    public static final int REQUEST_UPDATE = 200;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    private final int ALERT_DIALOG_CLOSE = 10;
    private final int ALERT_DIALOG_DELETE = 20;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add_update);

        edtNama = findViewById(R.id.edt_nama);
        edtNohp = findViewById(R.id.edt_nohp);
        edtAlamat = findViewById(R.id.edt_alamat);
        radioLaki = findViewById(R.id.laki);
        radioPerempuan = findViewById(R.id.perempuan);
        list_opsi = findViewById(R.id.opsi);
        Button btnSubmit = findViewById(R.id.btn_submit);

        formNoteHelper = FormNoteHelper.getInstance(getApplicationContext());
        formNoteHelper.open();

        formNote = getIntent().getParcelableExtra(EXTRA_NOTE);
        if (formNote != null) {
            position = getIntent().getIntExtra(EXTRA_POSITION, 0);
            isEdit = true;
        } else {
            formNote = new FormNote();
        }
        String actionBarTitle;
        String btnTitle;

        if (isEdit) {
            actionBarTitle = "Ubah";
            btnTitle = "Update";
            if (formNote != null) {
                edtNama.setText(formNote.getNama());
                edtNohp.setText(formNote.getNohp());
                edtAlamat.setText(formNote.getAlamat());
            }

        } else {
            actionBarTitle = "Tambah";
            btnTitle = "Simpan";
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        btnSubmit.setText(btnTitle);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_submit) {
//            ContentValues contentValues = new ContentValues();
            String nama = edtNama.getText().toString().trim();
            String nohp = edtNohp.getText().toString().trim();
            String alamat = edtAlamat.getText().toString().trim();
            if (radioLaki.isChecked()){
                Toast.makeText(NoteAddUpdateActivity.this, "Gender Dipilih : Laki-laki", Toast.LENGTH_SHORT).show();
                //contentValues.put(JENISKELAMIN, radioLaki.getText().toString());
                tmp = radioLaki.getText().toString();

            }else{
                Toast.makeText(NoteAddUpdateActivity.this, "Gender Dipilih : perempuan", Toast.LENGTH_SHORT).show();
                tmp =radioLaki.getText().toString();
            }
            String opsi = tmp;


            if (TextUtils.isEmpty(nama)) {
                edtNama.setError("Field can not be blank");
                return;
            }
            formNote.setNama(nama);
            formNote.setAlamat(alamat);
            formNote.setNohp(nohp);
            formNote.setJenisKelamin(opsi);
            formNote.setLokasi("tempo");

            Intent intent = new Intent();
            intent.putExtra(EXTRA_NOTE, formNote);
            intent.putExtra(EXTRA_POSITION, position);

            ContentValues values = new ContentValues();
            values.put(NAMA, nama);
            values.put(NOHP, nohp);
            values.put(ALAMAT, alamat);
            values.put(JENISKELAMIN, opsi);
            values.put(LOKASI, "temp");



            if (isEdit) {
                long result = formNoteHelper.update(String.valueOf(formNote.getId()), values);
                if (result > 0) {
                    setResult(RESULT_UPDATE, intent);
                    finish();
                } else {
                    Toast.makeText(NoteAddUpdateActivity.this, "Gagal mengupdate data 01", Toast.LENGTH_SHORT).show();
                }
            } else {
                formNote.setDate(getCurrentDate());
                values.put(DATE, getCurrentDate());
                long result = formNoteHelper.insert(values);

                if (result > 0) {
                    formNote.setId((int) result);
                    setResult(RESULT_ADD, intent);
                    finish();
                } else {
                    Toast.makeText(NoteAddUpdateActivity.this, "Gagal menambah data 02", Toast.LENGTH_SHORT).show();
                    Toast.makeText(NoteAddUpdateActivity.this, "result val " +result, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();

        return dateFormat.format(date);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isEdit) {
            getMenuInflater().inflate(R.menu.menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete) {
            showAlertDialog(ALERT_DIALOG_DELETE);
        } else if (id == android.R.id.home) {
            showAlertDialog(ALERT_DIALOG_CLOSE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        showAlertDialog(ALERT_DIALOG_CLOSE);
    }

    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;
        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMessage = "Apakah anda ingin membatalkan perubahan pada form?";
        } else {
            dialogMessage = "Apakah anda yakin ingin menghapus item ini?";
            dialogTitle = "Hapus Note";
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", (dialog, id) -> {
                    if (isDialogClose) {
                        finish();
                    } else {
                        long result = formNoteHelper.deleteById(String.valueOf(formNote.getId()));
                        if (result > 0) {
                            Intent intent = new Intent();
                            intent.putExtra(EXTRA_POSITION, position);
                            setResult(RESULT_DELETE, intent);
                            finish();
                        } else {
                            Toast.makeText(NoteAddUpdateActivity.this, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Tidak", (dialog, id) -> dialog.cancel());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
