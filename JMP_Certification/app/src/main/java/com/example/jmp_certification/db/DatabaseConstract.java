package com.example.jmp_certification.db;

import android.provider.BaseColumns;

public class DatabaseConstract {
    public static final class NoteColumns implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String NAMA = "title";
        public static final String NOHP = "description";
        public static final String DATE = "date";
        public static final String JENISKELAMIN = "jk";
        public static final String ALAMAT = "Alamat";
        public static final String LOKASI = "Lokasi";
    }
}
