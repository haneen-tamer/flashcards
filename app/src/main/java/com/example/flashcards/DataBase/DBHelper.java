package com.example.flashcards.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.flashcards.models.Card;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.Folder;

public class DBHelper extends SQLiteOpenHelper {

    private static String databaseName= "Database";

    SQLiteDatabase Database;
    public DBHelper(Context context)
    {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table Folder(id integer primary key autoincrement, "+" title text not null , decks Deck)");
        db.execSQL("create table Deck(id integer primary key autoincrement, "+" title text not null , description text, folder_id integer, cards Card)");
        db.execSQL("create table Card(id integer primary key autoincrement, "+" term text not null , definition text, deck_id integer, mistake_count integer, learn_count integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists Folder");
        db.execSQL("drop table if exists Deck");
        db.execSQL("drop table if exists Card");
        onCreate(db);
    }

    public void AddFolder(Folder f)
    {
        ContentValues row = new ContentValues();
        //row.put("id",f.getId());
        row.put("title", f.getTitle());
        //row.put("Deck", f. );
        Database= getWritableDatabase();
        Database.insert("Folder", null, row);
        Database.close();
    }
    public boolean AddDeck(Deck d)
    {
        ContentValues row = new ContentValues();
       // row.put("id", d.getId());
        row.put("title",d.getTitle());
        row.put("description", d.getDescription());
        row.put("folder_id", d.getFolder_id());
        Database= getWritableDatabase();
        Database.insert("Deck", null, row);

        Database.close();
        return true;
    }

    public void AddCard(Card c)
    {
        ContentValues row = new ContentValues();
       // row.put("id",c.getId());
        row.put("term", c.getTerm());
        row.put("definition", c.getDefinition());
        row.put("deck_id", c.getDeck_id());
        row.put("mistake_count", c.getMistake_count());
        row.put("learn_count", c.getLearn_count());
        Database= getWritableDatabase();
        Database.insert("Card", null, row);
        Database.close();
    }


    boolean IsFamiliar()
    {
        return  false;
    }
    boolean IsLearnt()
    {
        return  false;

    }
    boolean IsDifficult()
    {
        return  false;
    }

    public void RemoveCard(Card c)
    {
        Database= getWritableDatabase();
        Database.delete("Card", "id='"+ c.getId() +"'", null);
        Database.close();
    }
    public void RemoveDeck(Deck d)
    {
        Database= getWritableDatabase();
        Database.delete("Card", "id='"+ d.getId() +"'", null);
        Database.close();
    }
    public void RemoveFolder(Folder f)
    {
        Database= getWritableDatabase();
        Database.delete("Card", "id='"+ f.getId() +"'", null);
        Database.close();
    }

   
}
