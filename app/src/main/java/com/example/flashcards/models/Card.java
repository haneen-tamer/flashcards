package com.example.flashcards.models;

import java.util.ArrayList;

public class Card {
    private int id;
    private String term;
    private String definition;
    private int deck_id;
    private int mistake_count;
    private int learn_count;

    public static ArrayList<Card> getCards(int deck_id){
        ArrayList<Card> cards = new ArrayList<>();
        String [] terms = {"Emma", "North and South", "War and Peace", "Jane Eyre", "Middlemarch", "Oliver Twist"};
        String [] def = {"Jane Austen", "Elizabeth Gaskell", "Leo Tolstoy", "Charlotte Bronte", "George Elliot", "Charles Dickens"};
        for(int i=0; i<terms.length; i++){
            int m = (int) Math.round(Math.random()*10);
            int l = (int) Math.round(Math.random()*10);
            cards.add(new Card(i, terms[i], def[i], deck_id, m, l));
        }
        return cards;
    }

    public boolean isFamiliar(){
        return (learn_count<2);
    }

    public boolean isLearnt(){
        return (learn_count>=2 && mistake_count<learn_count);
    }

    public boolean isDifficult(){
        return (mistake_count>=2 || mistake_count>learn_count);
    }

    public Card(int id, String term, String definition, int deck_id, int mistake_count, int learn_count) {
        this.id = id;
        this.term = term;
        this.definition = definition;
        this.deck_id = deck_id;
        this.mistake_count = mistake_count;
        this.learn_count = learn_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(int deck_id) {
        this.deck_id = deck_id;
    }

    public int getMistake_count() {
        return mistake_count;
    }

    public void setMistake_count(int mistake_count) {
        this.mistake_count = mistake_count;
    }

    public int getLearn_count() {
        return learn_count;
    }

    public void setLearn_count(int learn_count) {
        this.learn_count = learn_count;
    }
}
