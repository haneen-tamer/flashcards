package com.example.flashcards.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rv;
    private ArrayList<Deck> decks;
    private DeckAdapter deckAdapter;

    public DecksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DecksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DecksFragment newInstance(String param1, String param2) {
        DecksFragment fragment = new DecksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
                //Toast.makeText(view.getContext(), "Here's a Snackbar", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), EditDActivity.class);
                startActivity(i);
            }
        });

        rv = root.findViewById(R.id.deckRecyclerView);
        decks = Deck.getDecks();
        deckAdapter = new DeckAdapter(decks);
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
}