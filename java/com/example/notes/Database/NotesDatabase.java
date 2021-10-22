package com.example.notes.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notes.Model.Notes;
import com.example.notes.Dao.NotesDao;

@Database(entities = {Notes.class}, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();

    public static NotesDatabase INSTANCE;

    public static NotesDatabase getDbInstance(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NotesDatabase.class,"Notes_Database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
