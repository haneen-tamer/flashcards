package com.example.flashcards.adapeters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards.R;
import com.example.flashcards.models.Card;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private ArrayList<Card> cards;

    public CardAdapter(ArrayList<Card> cards) {
        this.cards = cards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new CardAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card c = cards.get(position);
        holder.term.setText(c.getTerm());
        holder.def.setText(c.getDefinition());
        if(c.isDifficult()){
            holder.difficultStatus.setImageResource(R.drawable.difficult);
        }else if(c.isLearnt()){
            holder.learnStatus.setImageResource(R.drawable.learnt_active);
        }else if(c.isFamiliar()){
            holder.learnStatus.setImageResource(R.drawable.familiar_active);
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView term;
        TextView def;
        ImageView learnStatus;
        ImageView difficultStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            term = itemView.findViewById(R.id.cardTermTV);
            def = itemView.findViewById(R.id.cardDefTV);
            learnStatus = itemView.findViewById(R.id.learnStatusImage);
            difficultStatus = itemView.findViewById(R.id.difficultStatusImage);
        }
    }
}
