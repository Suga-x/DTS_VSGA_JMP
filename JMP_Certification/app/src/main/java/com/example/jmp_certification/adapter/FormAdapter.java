package com.example.jmp_certification.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmp_certification.CustomOnItemClickListener;
import com.example.jmp_certification.NoteAddUpdateActivity;
import com.example.jmp_certification.R;
import com.example.jmp_certification.entity.FormNote;

import java.util.ArrayList;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.NoteViewHolder>{
    private final ArrayList<FormNote> listFromNote = new ArrayList<>();
    private final Activity activity;
    public FormAdapter(Activity activity) {
        this.activity = activity;
    }
    public ArrayList<FormNote> getListCoffeeDrinkNotes() {
        return listFromNote;
    }
    public void setListCoffeeDrinkNotes(ArrayList<FormNote> listCoffeeDrinkNotes) {
        if (listCoffeeDrinkNotes.size() > 0) {
            this.listFromNote.clear();
        }
        this.listFromNote.addAll(listCoffeeDrinkNotes);
        notifyDataSetChanged();
    }
    public void addItem(FormNote formNote) {
        this.listFromNote.add(formNote);
        notifyItemInserted(listFromNote.size() -1);
    }

    public void updateItem(int position, FormNote formNote) {
        this.listFromNote.set(position, formNote);
        notifyItemChanged(position, formNote);
    }

    public void removeItem(int position) {
        this.listFromNote.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listFromNote.size());
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.tvNama.setText(listFromNote.get(position).getNama());
        holder.tvDate.setText(listFromNote.get(position).getDate());
        holder.tvAlamat.setText(listFromNote.get(position).getAlamat());
        holder.tvJenisKelamin.setText(listFromNote.get(position).getJenisKelamin());
        holder.cvNote.setOnClickListener(new CustomOnItemClickListener(position, (view, position1) -> {
            Intent intent = new Intent(activity, NoteAddUpdateActivity.class);
            intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, position1);
            intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, listFromNote.get(position1));
            activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE);
        }));
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    static class NoteViewHolder extends RecyclerView.ViewHolder {
        final TextView tvNama, tvAlamat, tvDate, tvJenisKelamin;
        final CardView cvNote;

        NoteViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvJenisKelamin = itemView.findViewById(R.id.tv_jk);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            cvNote = itemView.findViewById(R.id.cv_item_note);
        }
    }
}
