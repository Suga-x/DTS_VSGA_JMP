package com.example.jmp_certification.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class FormNote implements Parcelable {
    private int id;
    private String Nama;
    private String jenisKelamin;
    private String nohp;
    private String date;
    private String alamat;
    private String lokasi;








    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.id);
        dest.writeString(this.Nama);
        dest.writeString(this.jenisKelamin);
        dest.writeString(this.nohp);
        dest.writeString(this.date);
        dest.writeString(this.alamat);
        dest.writeString(this.lokasi);
    }
    public FormNote(int id, String nama, String nohp, String date, String jenisKelamin, String alamat, String lokasi) {
    }
    public FormNote() {
        this.id = id;
        this.Nama = Nama;
        this.jenisKelamin = jenisKelamin;
        this.nohp = nohp;
        this.date = date;
        this.alamat = alamat;
        this.lokasi = lokasi;
    }
    public FormNote(Parcel in) {
        this.id = in.readInt();
        this.Nama = in.readString();
        this.jenisKelamin = in.readString();
        this.nohp = in.readString();
        this.date = in.readString();
        this.alamat = in.readString();
        this.lokasi = in.readString();
    }
    public static final Creator<FormNote> CREATOR = new Creator<FormNote>() {
        @Override
        public FormNote createFromParcel(Parcel in) {
            return new FormNote(in);
        }

        @Override
        public FormNote[] newArray(int size) {
            return new FormNote[size];
        }
    };

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
}
