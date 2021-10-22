package com.example.notes.ViewModel;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.notes.Model.Notes;
import com.example.notes.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;

    public NotesViewModel( Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNotes = repository.getAllNotes;
    }

    public void notesInsert(Notes note)
    {
        repository.insertNotes(note);
    }

    public void notesDelete(int id)
    {
        repository.deleteNotes(id);
    }

    public void notesUpdate(Notes note)
    {
        repository.updateNotes(note);
    }
}
