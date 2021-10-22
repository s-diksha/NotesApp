package com.example.notes.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Activity.UpdateNotes;
import com.example.notes.MainActivity;
import com.example.notes.Model.Notes;
import com.example.notes.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotesList;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
        allNotesList = new ArrayList<>(notes);
    }

    @Override
    public notesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(NotesAdapter.notesViewHolder holder, int position) {
        Notes n = notes.get(position);

        holder.title.setText(n.title);
        holder.note_text.setText(n.note);
        holder.note_date.setText(n.notesDate);
        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(mainActivity, UpdateNotes.class);
            i.putExtra("id",n.id);
            i.putExtra("title", n.title);
            i.putExtra("date", n.notesDate);
            i.putExtra("note", n.note);
            mainActivity.startActivity(i);
        });

        holder.itemView.setOnLongClickListener(v -> {
            Log.e("POS", "position " + position);
            swapItem(position, 0);
            notifyDataSetChanged();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void searchNotes(List<Notes> filterNote)
    {
        this.notes = filterNote;
        notifyDataSetChanged();
    }

    public void swapItem(int fromPosition,int toPosition){
        Collections.swap(this.notes, fromPosition, toPosition);
        Log.e("POS", "position " + fromPosition + " " + toPosition);
        notifyItemMoved(fromPosition, toPosition);
        notifyDataSetChanged();
    }

    class notesViewHolder extends RecyclerView.ViewHolder
    {
        TextView title, note_date, note_text;
        public notesViewHolder( View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notes_title1);
            note_text = itemView.findViewById(R.id.notes_snippet);
            note_date = itemView.findViewById(R.id.notes_date1);
        }
    }
}
