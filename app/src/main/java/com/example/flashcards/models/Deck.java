package com.example.flashcards.models;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Deck {
    private int id;
    private String title;
    private String description;
    private int folder_id;
    private ArrayList<Card> cards;

    public static ArrayList<Deck> getDecks(){
        ArrayList<Deck> decks = new ArrayList<>();
        for(int i=0; i<5; i++){
            decks.add(new Deck(i,"Fiction "+i, "Authors and popular work", Card.getCards(i)));
        }
        return decks;
    }

    public static ArrayList<Deck> getDecks(int folder_id){
        ArrayList<Deck> decks = new ArrayList<>();
        for(int i=0; i<5; i++){
            decks.add(new Deck(i,"Fiction "+i, "Authors and popular work", folder_id,Card.getCards(i)));
        }
        return decks;
    }

    public Deck(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.folder_id = folder_id;
        cards = new ArrayList<Card>();
    }
    public Deck(int id, String title, String description, ArrayList<Card> cards) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.folder_id = folder_id;
        this.cards = cards;
    }
    public Deck(int id, String title, String description, int folder_id, ArrayList<Card> cards) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.folder_id = folder_id;
        this.cards = cards;
    }

    public void addCard(Card c){
        cards.add(c);
    }

    public void removeCard(int position){
        cards.remove(position);
    }

    public int getCardsCount(){return cards.size();}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(int folder_id) {
        this.folder_id = folder_id;
    }
}
