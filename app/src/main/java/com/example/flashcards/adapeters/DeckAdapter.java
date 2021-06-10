package com.example.flashcards.adapeters;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.activities.EditDActivity;
import com.example.flashcards.fragments.AddToFolderDialog;
import com.example.flashcards.models.Deck;

import java.util.ArrayList;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.ViewHolder> {
    private ArrayList<Deck> decks;
    private int colorLast;
    private DBHelper helper;

    // Define listener member variable
    private OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public DeckAdapter(ArrayList<Deck> decks, DBHelper helper) {
        this.decks = decks;
        colorLast = R.color.yellow;
        this.helper = helper;
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

//        holder.getColorBar().setBackgroundColor(holder.itemView.getResources().getColor(colorLast));
//        if(colorLast==R.color.yellow)
//            colorLast=R.color.primary_dark;
//        else
//            colorLast = R.color.yellow;
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
                            if (position != RecyclerView.NO_POSITION){
                            Intent i = new Intent(v.getContext(), EditDActivity.class);
                            i.putExtra("deck",d);
                            v.getContext().startActivity(i);
                            }
                            return true;
                        case R.id.deck_menu_del:
                            if (position != RecyclerView.NO_POSITION) {
                                helper.RemoveDeck(d);
                                decks.remove(d);
                                notifyItemRemoved(position);
                            }
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
        private LinearLayout colorBar;

        public LinearLayout getColorBar() {
            return colorBar;
        }

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
            description = itemView.findViewById(R.id.Definition);
            options = itemView.findViewById(R.id.deckItemOptionsMenu);
            colorBar = itemView.findViewById(R.id.colorLinearLayout);
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
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(itemView,position);
            }
        }
    }
}
