package com.example.notes.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.example.notes.Model.Notes;
import com.example.notes.R;
import com.example.notes.ViewModel.NotesViewModel;
import com.example.notes.databinding.ActivityAddNotesBinding;

import java.util.Date;


public class AddNotesActivity extends AppCompatActivity {

    ActivityAddNotesBinding binding;
    String title, subtitle, notes_data;
    NotesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding = ActivityAddNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.notesAddBtn.setOnClickListener(v -> {
            title = binding.notesTitle.getText().toString();
            subtitle = binding.notesSubtitle.getText().toString();
            notes_data = binding.notesData.getText().toString();

            createNotes(title, subtitle, notes_data);
        });
    }

    private void createNotes(String title, String subtitle, String notes_data) {

        Date nDate = new Date();
        CharSequence date_seq = DateFormat.format("MMMM d,yyyy", nDate.getTime());

        Notes notes1 = new Notes();
        notes1.title = title;
        notes1.note = notes_data;
        notes1.notesDate = date_seq.toString();

        viewModel.notesInsert(notes1);

        Toast.makeText(this, "Notes Created.", Toast.LENGTH_SHORT).show();
        finish();

    }
}