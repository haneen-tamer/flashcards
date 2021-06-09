package com.example.flashcards.utils;

import android.database.sqlite.SQLiteDatabase;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.models.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class QuizController {
    private ArrayList<Card> cards;

    public int getCorrectCount() {
        return correctCount;
    }

    private int correctCount;
    private Queue<Card> shuffledCards;
    Card currentCard;
    DBHelper helper;

    public QuizController(ArrayList<Card> cards, DBHelper helper) {
        this.cards = cards;
        this.helper=helper;
        correctCount=0;
        shuffledCards = new LinkedList<>();
        shuffleCards();
    }

    public void checkAnswer(String answer){
        if(currentCard.getTerm().equals(answer)) {
            correctCount++;
            currentCard.incLearnCount();
        }else{
            currentCard.incMistakeCount();
        }
        helper.updateCard(currentCard);
    }

    private void shuffleCards(){
        HashSet<Integer> selectedCards = new HashSet<Integer>();

        while(shuffledCards.size()!=cards.size()){
            int indx = (int) Math.round(Math.random() * (cards.size() - 1));
            if(selectedCards.contains(cards.get(indx).getId())) continue;
            selectedCards.add(cards.get(indx).getId());
            shuffledCards.add(cards.get(indx));
        }
    }

    public String getNextDef(){
        currentCard = shuffledCards.remove();
        return currentCard.getDefinition();
    }

    public boolean isDone(){
        return shuffledCards.isEmpty();
    }

}
