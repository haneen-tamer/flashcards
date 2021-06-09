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

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.adapeters.CardAdapter;
import com.example.flashcards.adapeters.ListCardAdapter;
import com.example.flashcards.fragments.AddToFolderDialog;
import com.example.flashcards.models.Deck;

public class ViewDeckActivity extends AppCompatActivity {

    Deck d;
    RecyclerView cardsRV;
    CardAdapter cardAdapter;
    DBHelper helper;
    TextView title;
    TextView description;
    TextView cardCount;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_deck_edit:
                //Toast.makeText(getApplicationContext(), R.string.edit, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ViewDeckActivity.this, EditDActivity.class);
                i.putExtra("deck",d);
                startActivity(i);
                return true;
            case R.id.view_deck_addtofolder:
//                Toast.makeText(getApplicationContext(), R.string.add_to_folder, Toast.LENGTH_SHORT).show();
                AddToFolderDialog dialog = new AddToFolderDialog(d);
                dialog.show(getSupportFragmentManager(), "dialog");
                return true;
//            case android.R.id.home:
//                onBackPressed();
//                return true;
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
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        helper = new DBHelper(getApplicationContext());
        title = findViewById(R.id.titleTV);
        description = findViewById(R.id.descriptionTV);
        cardCount = findViewById(R.id.cardCountTV);
        cardsRV = findViewById(R.id.cardsRV);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            d= (Deck) extras.getSerializable("deck");
        }

        findViewById(R.id.learnBtn).setOnClickListener(v -> {
            Intent i = new Intent(ViewDeckActivity.this, LearnActivity.class);
            i.putExtra("deck", d);
            startActivity(i);
        });

        findViewById(R.id.quizBtn).setOnClickListener(v -> {
            Intent i = new Intent(ViewDeckActivity.this, QuizActivity.class);
            i.putExtra("deck", d);
            startActivity(i);
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        d = helper.getDeckByID(d.getId());
        cardAdapter = new CardAdapter(d.getCards());
        cardsRV.setAdapter(cardAdapter);
        cardsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        title.setText(d.getTitle());
        description.setText(d.getDescription());
        cardCount.setText(""+d.getCardsCount()+" cards");
    }
}