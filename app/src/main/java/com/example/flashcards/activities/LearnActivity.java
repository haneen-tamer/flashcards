package com.example.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.fragments.CustomDialog;
import com.example.flashcards.models.Card;
import com.example.flashcards.models.Deck;
import com.example.flashcards.utils.LearnController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class LearnActivity extends AppCompatActivity {
    Deck d;
    LearnController learnController;
    String[] currentOptions;
    Card currentCard;
    Queue<Card> cards;
    TextView[] optionsTV;
    FrameLayout[] optionsFL;
    TextView term;
    DBHelper helper;

    TextView clicked;

    int correctCount;
    AlertDialog.Builder dialogBuilder;
     Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(R.string.learn);

        correctCount=0;
        helper = new DBHelper(getApplicationContext());

        optionsTV = new TextView[4];
        optionsFL = new FrameLayout[4];

        optionsTV[0] = findViewById(R.id.option1LearnTV);
        optionsFL[0] = findViewById(R.id.option1Frame);

        optionsTV[1] = findViewById(R.id.option2LearnTV);
        optionsFL[1] = findViewById(R.id.option2Frame);

        optionsTV[2] = findViewById(R.id.option3LearnTV);
        optionsFL[2] = findViewById(R.id.option3Frame);

        optionsTV[3] = findViewById(R.id.option4LearnTV);
        optionsFL[3] = findViewById(R.id.option4Frame);

        term = findViewById(R.id.termLearnTV);


        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            d = (Deck) extras.getSerializable("deck");
            learnController = new LearnController(d.getCards());
            shuffleCards(d.getCards());
            currentCard = pickCard();
            currentOptions = learnController.getOptions(currentCard);
            displayOptions();

        }


        for(int i =0; i<4; i++){
            optionsTV[i].setOnClickListener(v -> {
                clicked = (TextView) v;
                handleClick();
            });
        }


    }

    private void displayOptions(){
        term.setText(currentCard.getTerm());
        for(int i=0; i<4; i++){
            optionsTV[i].setText(currentOptions[i]);
            optionsTV[i].setTextColor(getResources().getColor(R.color.primary_dark));
            optionsTV[i].setBackgroundColor(getResources().getColor(R.color.grey));
            optionsFL[i].setBackgroundColor(getResources().getColor(R.color.primary));
        }
    }


    private Card pickCard(){
        return cards.remove();
    }

    private void shuffleCards(ArrayList<Card> deckCards){
        HashSet<Integer> selectedCards = new HashSet<Integer>();
        this.cards = new LinkedList<>();
        while(cards.size()!=deckCards.size()){
            int indx = (int) Math.round(Math.random() * (deckCards.size() - 1));
            if(selectedCards.contains(deckCards.get(indx).getId())) continue;
            selectedCards.add(deckCards.get(indx).getId());
            cards.add(deckCards.get(indx));
        }
    }

    private void handleClick(){
        if(learnController.isCorrect(currentCard.getTerm(), clicked.getText().toString())){
            correctCount++;

            currentCard.incLearnCount();
            helper.updateCard(currentCard);
        }else{
            currentCard.incMistakeCount();
            helper.updateCard(currentCard);
            clicked.setBackgroundColor(getResources().getColor(R.color.red));
            clicked.setTextColor(getResources().getColor(R.color.white));

            FrameLayout fl = (FrameLayout) clicked.getParent();
            fl.setBackgroundColor(getResources().getColor(R.color.red));
        }
        int correct = learnController.getCurrentCorrectIndx();
        optionsTV[correct].setBackgroundColor(getResources().getColor(R.color.green));
        optionsTV[correct].setTextColor(getResources().getColor(R.color.white));
        optionsFL[correct].setBackgroundColor(getResources().getColor(R.color.green));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.done_action:
                if(cards.isEmpty()){
                   // Toast.makeText(getApplicationContext(), "Correct: "+correctCount, Toast.LENGTH_SHORT).show();

                    CustomDialog dialog = new CustomDialog();
                    dialog.count= correctCount;
                    dialog.show(getSupportFragmentManager(),"CustomDialog");


                }else{
                    currentCard = pickCard();
                    currentOptions = learnController.getOptions(currentCard);
                    displayOptions();
                }

                return true;
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}