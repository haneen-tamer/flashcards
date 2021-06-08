package com.example.flashcards.utils;

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

    public QuizController(ArrayList<Card> cards) {
        this.cards = cards;
        correctCount=0;
        shuffledCards = new LinkedList<>();
        shuffleCards();
    }

    public void checkAnswer(String answer){
        if(currentCard.getTerm().equals(answer)) correctCount++;
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
