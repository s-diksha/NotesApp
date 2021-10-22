package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.example.notes.Activity.AddNotesActivity;
import com.example.notes.Adapter.NotesAdapter;
import com.example.notes.Model.Notes;
import com.example.notes.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btn;
    NotesViewModel viewModel;
    RecyclerView rview;
    CardView cardView;
    NotesAdapter nAdapter;
    List<Notes> filterNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rview = findViewById(R.id.listView);
        btn = findViewById(R.id.notes_add_btn);
        cardView = findViewById(R.id.card_view);
        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);


        btn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddNotesActivity.class));
        });

        viewModel.getAllNotes.observe(this, notes -> {
            filterNameList = notes;
            rview.setLayoutManager(new GridLayoutManager(this ,2 ));
            nAdapter = new NotesAdapter(MainActivity.this, notes);
            rview.setAdapter(nAdapter);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView search = (SearchView)menuItem.getActionView();
        search.setQueryHint("Search");

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                notesFilter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    void notesFilter(String text)
    {
        ArrayList<Notes> filterNames = new ArrayList<>();
        for(Notes notes : this.filterNameList)
        {
            if(notes.title.contains(text) || notes.note.contains(text))
            {
                filterNames.add(notes);
            }
        }
        this.nAdapter.searchNotes(filterNames);
    }

}