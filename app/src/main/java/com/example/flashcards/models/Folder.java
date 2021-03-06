package com.example.flashcards.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Folder implements Serializable {
    private int id;
    private String title;

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
    }

    private ArrayList<Deck> decks;

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public static ArrayList<Folder> getFolders(){
        ArrayList<Folder> folders = new ArrayList<>();
        for(int i=0; i<3; i++){
            folders.add(new Folder(i, "lit "+i, Deck.getDecks(i)));
        }
        return folders;
    }

    public Folder(int id, String title) {
        this.id = id;
        this.title = title;
        decks = new ArrayList<Deck>();
    }

    public Folder(int id, String title, ArrayList<Deck> decks) {
        this.id = id;
        this.title = title;
        this.decks = decks;
    }

    public void addDeck(Deck c){
        decks.add(c);
    }

    public void removeDeck(int position){
        decks.remove(position);
    }

    public int getDecksCount(){return decks.size();}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
