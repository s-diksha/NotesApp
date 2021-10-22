package com.example.notes.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notes.Dao.NotesDao;
import com.example.notes.Database.NotesDatabase;
import com.example.notes.Model.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;

    public NotesRepository(Application application)
    {
        NotesDatabase database = NotesDatabase.getDbInstance(application);
        notesDao = database.notesDao();

        getAllNotes = notesDao.getAllNotes();
    }

    public void insertNotes(Notes note)
    {
        notesDao.insertNotes(note);
    }

    public void deleteNotes(int id)
    {
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes note)
    {
        notesDao.updateNotes(note);
    }

}
