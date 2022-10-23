package com.example.jmp_certification;

import android.database.Cursor;

import com.example.jmp_certification.db.DatabaseConstract;
import com.example.jmp_certification.entity.FormNote;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<FormNote> mapCursorToArrayList(Cursor notesCursor) {
        ArrayList<FormNote> notesList = new ArrayList<>();

        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(DatabaseConstract.NoteColumns._ID));
            String nama = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseConstract.NoteColumns.NAMA));
            String nohp = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseConstract.NoteColumns.NOHP));
            String date = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseConstract.NoteColumns.DATE));
            String jenisKelamin = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseConstract.NoteColumns.JENISKELAMIN));
            String alamat = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseConstract.NoteColumns.ALAMAT));
            String lokasi = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseConstract.NoteColumns.LOKASI));
            notesList.add(new FormNote(id, nama, nohp, date, jenisKelamin,alamat,lokasi));
        }
        return notesList;
    }
}
