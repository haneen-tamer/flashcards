package com.example.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcards.R;
import com.example.flashcards.adapeters.CardAdapter;
import com.example.flashcards.models.Deck;

public class ViewDeckActivity extends AppCompatActivity {

    Deck d;
    RecyclerView cardsRV;
    CardAdapter cardAdapter;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_deck_edit:
                Toast.makeText(getApplicationContext(), R.string.edit, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.view_deck_addtofolder:
                Toast.makeText(getApplicationContext(), R.string.add_to_folder, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_deck_toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_deck);

        Toolbar myToolbar = findViewById(R.id.viewDeckToolbar);
        setSupportActionBar(myToolbar);
//        myToolbar.setNavigationIcon(R.drawable.arrow_left);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            d= (Deck) extras.getSerializable("deck");

            TextView title = findViewById(R.id.titleTV);
            title.setText(d.getTitle());

            TextView description = findViewById(R.id.descriptionTV);
            description.setText(d.getDescription());

            TextView cardCount = findViewById(R.id.cardCountTV);
            cardCount.setText(""+d.getCardsCount()+" cards");

            cardsRV = findViewById(R.id.cardsRV);
            cardAdapter = new CardAdapter(d.getCards());
            cardsRV.setAdapter(cardAdapter);
            cardsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        }

        findViewById(R.id.learnBtn).setOnClickListener(v -> {
            Intent i = new Intent(ViewDeckActivity.this, LearnActivity.class);
            i.putExtra("deck", d);
            startActivityForResult(i, 1);
        });



    }
}