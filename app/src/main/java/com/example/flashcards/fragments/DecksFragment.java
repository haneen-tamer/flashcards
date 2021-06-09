package com.example.flashcards.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.activities.MainActivity;
import com.example.flashcards.activities.ViewDeckActivity;
import com.example.flashcards.activities.CreateDActivity;
import com.example.flashcards.activities.EditDActivity;
import com.example.flashcards.adapeters.DeckAdapter;
import com.example.flashcards.adapeters.FolderAdapter;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.Folder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DecksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DecksFragment extends Fragment {

    private RecyclerView rv;
    private ArrayList<Deck> decks;
    private DeckAdapter deckAdapter;
    private DBHelper helper;

    public DecksFragment() {
        // Required empty public constructor
    }


    public static DecksFragment newInstance() {
        DecksFragment fragment = new DecksFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_decks, container, false);
        FloatingActionButton fab = root.findViewById(R.id.deckFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CreateDActivity.class);
                startActivity(i);
            }
        });

        rv = root.findViewById(R.id.deckRecyclerView);
        helper = new DBHelper(getContext());
        decks = new ArrayList<>(); //helper.getAllDecks();
        deckAdapter = new DeckAdapter(decks, helper);
        deckAdapter.setOnItemClickListener(new DeckAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                    Deck d = decks.get(position);
                    Intent i = new Intent(getActivity(), ViewDeckActivity.class);
                    i.putExtra("deck", d);
                    startActivity(i);
                }
            }
        });
        rv.setAdapter(deckAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        int size=decks.size();
        decks.clear();
        deckAdapter.notifyItemRangeRemoved(0, size);
        for(Deck d:helper.getAllDecks()) {
            decks.add(d);
            deckAdapter.notifyItemInserted(decks.size()-1);
        }
        Log.i("wtf2", "idk what's happeneing");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        Log.i("wtf", "idk what's happeneing");
    }
}