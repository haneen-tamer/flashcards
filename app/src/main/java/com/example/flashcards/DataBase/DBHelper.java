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

import java.util.ArrayList;

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
        db.execSQL("create table Folder(id integer primary key, "+" title text not null)");
        db.execSQL("create table Deck(id integer primary key, "+
                " title text not null , description text, folder_id integer, " +
                "FOREIGN KEY(folder_id) REFERENCES Folder (id))");
        db.execSQL("create table Card(id integer primary key, "+
                " term text not null , definition text, deck_id integer, mistake_count integer, learn_count integer, " +
                "FOREIGN KEY(deck_id) REFERENCES Deck (id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists Card");
        db.execSQL("drop table if exists Deck");
        db.execSQL("drop table if exists Folder");
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
        int id = getNextDeckID();
        row.put("id", id);
        row.put("title",d.getTitle());
        row.put("description", d.getDescription());
        row.put("folder_id", d.getFolder_id());
        Database= getWritableDatabase();
        Database.insert("Deck", null, row);
        for(Card c:d.getCards()){
            c.setDeck_id(id);
            AddCard(c);
        }
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
        Database.delete("Deck", "id='"+ d.getId() +"'", null);
        Database.close();
    }
    public void RemoveFolder(Folder f)
    {
        Database= getWritableDatabase();
        Database.delete("Card", "id='"+ f.getId() +"'", null);
        Database.close();
    }

    public void updateCard(Card c){
        Database = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("term", c.getTerm());
        row.put("definition", c.getDefinition());
        row.put("mistake_count", c.getMistake_count());
        row.put("learn_count", c.getLearn_count());
        Database.update("Card", row, "id == ?", new String []{""+c.getId()});
        Database.close();
    }

    public void updateDeck(Deck d){
        Database = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("title", d.getTitle());
        row.put("description", d.getDescription());
        row.put("folder_id", d.getFolder_id());
        Database.update("Deck", row, "id == ?", new String []{""+d.getId()});
        Database.close();
        for(Card c:d.getCards()){
            updateCard(c);
        }
    }

    public void renameFolder(Folder f){
        Database = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("title", f.getTitle());
        Database.update("Folder", row, "id == ?", new String []{""+f.getId()});
        Database.close();
    }

    public void addDeckToFolder(Deck d, Folder f){
        Database = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("folder_id", f.getId());
        Database.update("Deck", row, "id == ?", new String []{""+d.getId()});
        Database.close();
    }

    public void removeDeckFromFolder(Deck d){
        Database = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("folder_id", 0);
        Database.update("Deck", row, "id == ?", new String []{""+d.getId()});
        Database.close();
    }


    public int getNextCardID(){
        Database = getReadableDatabase();
        Cursor c = Database.rawQuery("select max(id) from Card", null);
        if(c!=null)
            c.moveToFirst();
        return c.getInt(0)+1;
    }

    public int getNextDeckID(){
        Database = getReadableDatabase();
        Cursor c = Database.rawQuery("select max(id) from Deck", null);
        if(c!=null)
            c.moveToFirst();
        return c.getInt(0)+1;
    }

    public int getNextFolderID(){
        Database = getReadableDatabase();
        Cursor c = Database.rawQuery("select max(id) from Folder", null);
        if(c!=null)
            c.moveToFirst();
        return c.getInt(0)+1;
    }

    public Deck getDeckByID(int id){
        Database = getReadableDatabase();

        String [] args = {""+id};
        Cursor dCursor = Database.rawQuery("select * from Deck where id == ?", args);
        if(dCursor!=null)
            dCursor.moveToFirst();
        Deck d = new Deck(id,
                dCursor.getString(1),
                dCursor.getString(2),
                dCursor.getInt(3),
                getCardsByDeckID(id, Database));
        dCursor.moveToNext();
        Database.close();
        return d;
    }

    public ArrayList<Deck> getAllDecks(){
        ArrayList<Deck> decks = new ArrayList<>();
        Database = getReadableDatabase();
        String [] rowDetails = {"id", "title", "description", "folder_id"};
        Cursor dCursor = Database.query("Deck", rowDetails, null, null, null, null, null);
        if(dCursor!=null)
            dCursor.moveToFirst();

        while(!dCursor.isAfterLast()){
            int id = dCursor.getInt(0);
            Deck d = new Deck(id,
                    dCursor.getString(1),
                    dCursor.getString(2),
                    dCursor.getInt(3),
                    getCardsByDeckID(id, Database));
            decks.add(d);
            dCursor.moveToNext();
        }

        Database.close();
//        for(Deck d: decks){
//            d.setCards(getCardsByDeckID(d.getId()));
//        }
        return decks;
    }

    private ArrayList<Card> getCardsByDeckID(int deck_id, SQLiteDatabase db){
        ArrayList<Card> cards = new ArrayList<>();
        String [] args = {""+deck_id};
        Cursor cCursor = db.rawQuery("select * from Card where deck_id == ?", args);
        if(cCursor!=null)
            cCursor.moveToFirst();
        while(!cCursor.isAfterLast()){
            int id = cCursor.getInt(0);
            Card c = new Card(id,
                    cCursor.getString(1),
                    cCursor.getString(2),
                    cCursor.getInt(3),
                    cCursor.getInt(4),
                    cCursor.getInt(5));
            cards.add(c);
            cCursor.moveToNext();
        }
        return cards;
    }

    public ArrayList<Folder> getAllFolders(){
        ArrayList<Folder> folders = new ArrayList<>();
        Database = getReadableDatabase();

        String [] rowDetails = {"id", "title"};
        Cursor dCursor = Database.query("Folder", rowDetails, null, null, null, null, null);
        if(dCursor!=null)
            dCursor.moveToFirst();

        while(!dCursor.isAfterLast()){
            int id = dCursor.getInt(0);
            Folder f = new Folder(id,
                    dCursor.getString(1),
                    getDecksByFolderID(id, Database));
            folders.add(f);
            dCursor.moveToNext();
        }

        Database.close();
//        for(Folder f:folders){
//            f.setDecks(getDecksByFolderID(f.getId()));
//        }
        return folders;
    }

    private ArrayList<Deck> getDecksByFolderID(int folder_id, SQLiteDatabase db){
        ArrayList<Deck> decks = new ArrayList<>();
//        Database = getReadableDatabase();

        String [] args = {""+folder_id};
        Cursor dCursor = db.rawQuery("select * from Deck where folder_id == ?", args);
        if(dCursor!=null)
            dCursor.moveToFirst();
        while(!dCursor.isAfterLast()){
            int id = dCursor.getInt(0);
            Deck d = new Deck(dCursor.getInt(0),
                    dCursor.getString(1),
                    dCursor.getString(2),
                    dCursor.getInt(3),
                    getCardsByDeckID(id, Database));
            decks.add(d);
            dCursor.moveToNext();
        }

//        Database.close();
        return decks;
    }
}
