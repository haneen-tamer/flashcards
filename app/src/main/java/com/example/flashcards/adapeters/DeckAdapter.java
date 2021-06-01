package com.example.flashcards.adapeters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards.R;
import com.example.flashcards.models.Deck;

import java.util.ArrayList;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.ViewHolder> {
    private ArrayList<Deck> decks;

    public DeckAdapter(ArrayList<Deck> decks) {
        this.decks = decks;
    }

    @NonNull
    @Override
    public DeckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deck_item, parent, false);
        return new DeckAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DeckAdapter.ViewHolder holder, int position) {
        Deck d = decks.get(position);
        holder.getTitle().setText(d.getTitle());
        holder.getCardCount().setText(""+d.getCardsCount()+" cards");
        holder.getDescription().setText(d.getDescription());
        holder.getOptions().setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(v.getContext(), holder.getOptions());
            //inflating menu from xml resource
            popup.inflate(R.menu.deck_menu);
            //adding click listener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.deck_menu_edit:
                            //handle menu1 click
                            Toast.makeText(v.getContext(), "edit", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.deck_menu_del:
                            //handle menu2 click
                            Toast.makeText(v.getContext(), "delete", Toast.LENGTH_SHORT).show();
                            return true;
                        default:
                            return false;
                    }
                }
            });
            //displaying the popup
            popup.show();
        });
    }

    @Override
    public int getItemCount() {
        return decks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView cardCount;
        private TextView description;
        private ImageButton options;

        public TextView getDescription() {
            return description;
        }

        public ImageButton getOptions() {
            return options;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.deckTitleCardTV);
            cardCount = itemView.findViewById(R.id.deckCardCountCardTV);
            description = itemView.findViewById(R.id.deckDescriptionCardTV);
            options = itemView.findViewById(R.id.deckItemOptionsMenu);
            itemView.setOnClickListener(this::onClick);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getCardCount() {
            return cardCount;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Deck d = decks.get(position);
                // We can access the data within the views
                Toast.makeText(v.getContext(), "Deck ID: "+d.getId(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
