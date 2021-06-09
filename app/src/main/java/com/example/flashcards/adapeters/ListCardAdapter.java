package com.example.flashcards.adapeters;

import android.content.Context;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.models.Card;
import com.example.flashcards.models.Deck;


import java.util.ArrayList;


public class ListCardAdapter extends RecyclerView.Adapter<ListCardAdapter.ViewHolder> {

    private ArrayList<Card> cards;
    DBHelper helper;

    public ListCardAdapter(ArrayList<Card> cards, DBHelper helper) {
        this.helper=helper;
        this.cards = cards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardviewlayout, parent, false);
        return new ListCardAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card c = cards.get(position);
        holder.term.setText(c.getTerm());
        holder.definition.setText(c.getDefinition());
        holder.term.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                c.setTerm(holder.term.getText().toString());
            }
        });
        holder.definition.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                c.setDefinition(holder.definition.getText().toString());
            }
        });
        holder.del.setOnClickListener(v -> {
            if (position != RecyclerView.NO_POSITION){
                helper.RemoveCard(c);
                cards.remove(position);
                notifyItemRemoved(position);
            }

        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText term;
        EditText definition;
        ImageButton del;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            term = itemView.findViewById(R.id.Termtext1);
            definition = itemView.findViewById(R.id.Definitiontext1);
            del = itemView.findViewById(R.id.delCardBtn);
        }
    }
}
