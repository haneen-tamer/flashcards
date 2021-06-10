package com.example.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.adapeters.DeckAdapter;
import com.example.flashcards.adapeters.ListCardAdapter;
import com.example.flashcards.models.Card;
import com.example.flashcards.models.Deck;

import java.util.ArrayList;

public class CreateDActivity extends AppCompatActivity {
    RecyclerView rv;
    DBHelper helper;
    Deck deck;
    ListCardAdapter adapter;
    Button addBtn;
    EditText title;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_d);

        Toolbar myToolbar = findViewById(R.id.editDeckToolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        helper = new DBHelper(getApplicationContext());
        title = findViewById(R.id.titleET);
        description = findViewById(R.id.descriptionET);
        addBtn = findViewById(R.id.addCardEditRVBtn);
        rv = findViewById(R.id.editCardsRV);

        deck = new Deck();
        deck.setId(helper.getNextDeckID());
        adapter = new ListCardAdapter(deck.getCards(), helper);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Card first = new Card("","",0,0);
        first.setDeck_id(deck.getId());
        deck.addCard(first);
        adapter.notifyItemInserted(0);
        rv.scrollToPosition(0);

        addBtn.setOnClickListener(v -> {
            Card c = new Card("","",0,0);
            c.setDeck_id(deck.getId());
            deck.addCard(c);
            adapter.notifyItemInserted(deck.getCardsCount()-1);
            rv.scrollToPosition(deck.getCardsCount()-1);
            //helper.AddCard(c);
        });
        title.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                deck.setTitle(title.getText().toString());
            }
        });
        description.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                deck.setDescription(description.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done_action:
                if(deck.getTitle().length()>0
                && deck.getDescription().length()>0
                && deck.getCards().size()>0){
                    helper.AddDeck(deck);
                    Intent i = new Intent(CreateDActivity.this, ViewDeckActivity.class);
                    i.putExtra("deck",deck);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Fill in the fields first!", Toast.LENGTH_SHORT).show();
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