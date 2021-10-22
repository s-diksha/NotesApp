package com.example.notes.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.Model.Notes;
import com.example.notes.ViewModel.NotesViewModel;
import com.example.notes.databinding.ActivityUpdateNotesBinding;
import com.example.notes.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotes extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    String stitle, snotes_data;
    int sid;
    NotesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sid = getIntent().getIntExtra("id",0);
        stitle = getIntent().getStringExtra("title");
        snotes_data = getIntent().getStringExtra("note");

        binding.titleUpdate.setText(stitle);
        binding.notesUpdate.setText(snotes_data);

        binding.notesUpdateBtn.setOnClickListener(v->{

            stitle = binding.titleUpdate.getText().toString();
            snotes_data = binding.notesUpdate.getText().toString();

            upnotes(stitle, snotes_data);
        });
    }

    private void upnotes(String stitle, String snotes_data) {
        Date nDate = new Date();
        CharSequence date_seq = DateFormat.format("MMMM d,yyyy", nDate.getTime());

        Notes notes2 = new Notes();
        notes2.id = sid;
        notes2.title = stitle;
        notes2.note = snotes_data;
        notes2.notesDate = date_seq.toString();

        viewModel.notesUpdate(notes2);

        Toast.makeText(this, "Notes Updated.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_icon)
        {
            BottomSheetDialog dialog = new BottomSheetDialog(UpdateNotes.this, R.style.BottomSheetStyle);

            View view = LayoutInflater.from(UpdateNotes.this).inflate(R.layout.delete_alert,
                    (LinearLayout)findViewById(R.id.bottom_alert));
            dialog.setContentView(view);

            TextView yes, no;
            yes = view.findViewById(R.id.view_yes_box);
            no = view.findViewById(R.id.view_no_box);

            yes.setOnClickListener(v -> {
                viewModel.notesDelete(sid);
                Toast.makeText(this, "Notes Deleted.", Toast.LENGTH_SHORT).show();
                finish();
            });

            no.setOnClickListener(v -> {
                dialog.dismiss();
            });

            dialog.show();
        }
        return true;
    }
}