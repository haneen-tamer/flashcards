package com.example.flashcards.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.FragmentTransitionSupport;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.adapeters.DeckAdapter;
import com.example.flashcards.fragments.DecksFragment;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.Folder;

public class ViewFolderActivity extends AppCompatActivity {

    Folder folder;
    DBHelper helper;
    TextView title;
    TextView deckCount;
    DeckAdapter deckAdapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_folder);

        Toolbar myToolbar = findViewById(R.id.viewFolderToolbar2);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        helper = new DBHelper(getApplicationContext());
        title = findViewById(R.id.folderTitleTV);
        deckCount = findViewById(R.id.deckCountTV);
        rv = findViewById(R.id.folderDecksRV);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            folder= (Folder) extras.getSerializable("folder");
            deckAdapter = new DeckAdapter(folder.getDecks(), helper);
            deckAdapter.setOnItemClickListener(new DeckAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
                    if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                        Deck d = folder.getDecks().get(position);
                        Intent i = new Intent(getApplicationContext(), ViewDeckActivity.class);
                        i.putExtra("deck", d);
                        startActivity(i);
                    }
                }
            });
            rv.setAdapter(deckAdapter);
            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        folder = helper.getFolderByID(folder.getId());
        deckAdapter = new DeckAdapter(folder.getDecks(), helper);
        rv.setAdapter(deckAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        title.setText(folder.getTitle());
        deckCount.setText(""+folder.getDecksCount()+" decks");
    }
}