package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.adapeters.DeckAdapter;
import com.example.flashcards.models.Card;
import com.example.flashcards.models.Deck;

import java.util.ArrayList;

public class CreateDActivity extends AppCompatActivity implements View.OnClickListener {
TextView Title;
TextView Desc;
LinearLayout layout;
CardView card;
Button Add;
DBHelper helper;
Button Create;
ArrayList<Card> cardsarray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_d);
        Title= (TextView) findViewById(R.id.Title);
        Desc = (TextView) findViewById(R.id.Description);
        helper= new DBHelper(this);
        layout = (LinearLayout) findViewById(R.id.linearLayout);
        Add = (Button) findViewById(R.id.ADDbtn);
        Add.setOnClickListener(this);

        Create= (Button) findViewById(R.id.Create);
        Create.setOnClickListener((view ->
        {
            Deck deck= new Deck(1,Title.getText().toString(),Desc.getText().toString(),cardsarray);
            if(helper.AddDeck(deck))
            {
                Toast.makeText(getApplicationContext(), "tamam", Toast.LENGTH_LONG).show();
            }


        }));

    }



    @Override
    public void onClick(View view) {
        addView();

    }
    private void addView()
    {
        View v= getLayoutInflater().inflate(R.layout.card_layout,null);
        EditText Term = (EditText) v.findViewById(R.id.Termtext1);
        EditText Definition = (EditText) v.findViewById(R.id.Definitiontext1);
        Card card = new Card(1,Term.getText().toString(),Definition.getText().toString(),1,2,3);
        cardsarray.add(card);
        CardView c= (CardView)v.findViewById(R.id.cardView1);
        layout.addView(v);
        helper.AddCard(card);
        Toast.makeText(getApplicationContext(), card.getDefinition(), Toast.LENGTH_LONG).show();
    }
}