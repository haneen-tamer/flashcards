package com.example.flashcards.utils;

import com.example.flashcards.models.Card;
import com.example.flashcards.models.Deck;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class LearnController {

    private ArrayList<Card> cards;
    private HashMap<String,String> dict;
    private int currentCorrect;

    public int getCurrentCorrectIndx() {
        return currentCorrect;
    }
//    private HashMap<String,String> reverseDict;

    public LearnController(ArrayList<Card> cards) {
        dict = new HashMap<>();
        this.cards = cards;
        for(int i=0; i<cards.size(); i++){
            dict.put(cards.get(i).getTerm(), cards.get(i).getDefinition());
//            reverseDict.put(cards.get(i).getDefinition(), cards.get(i).getTerm());
        }
    }

    public String[] getOptions(Card c){
        String [] options = new String[4];
        int c_index;
        for(int i=0; i<4; i++){
            do {
                c_index = (int) Math.round(Math.random() * (cards.size() - 1));
            }while(c.getId()==cards.get(c_index).getId());

            options[i] = cards.get(c_index).getDefinition();
        }
        int r_indx = (int) Math.round(Math.random()*3);
        currentCorrect=r_indx;
        options[r_indx] = c.getDefinition();
        return options;
    }

    public boolean isCorrect(String term, String def){
        return (dict.get(term).equals(def));
    }

    public String getCorrectDef(String term){
        return dict.get(term);
    }
}
