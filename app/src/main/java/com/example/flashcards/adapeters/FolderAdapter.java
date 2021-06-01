package com.example.flashcards.adapeters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards.R;
import com.example.flashcards.models.Folder;

import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {
    private ArrayList<Folder> folders;

    public FolderAdapter(ArrayList<Folder> folders) {
        this.folders = folders;
    }

    @NonNull
    @Override
    public FolderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.folder_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderAdapter.ViewHolder holder, int position) {
        Folder f = folders.get(position);
        holder.getTitle().setText(f.getTitle());
        holder.getDeckCount().setText(""+f.getDecksCount()+" decks");
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView deckCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.folderTitleCardTV);
            deckCount = itemView.findViewById(R.id.folderDeckCountCardTV);
            itemView.setOnClickListener(this::onClick);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDeckCount() {
            return deckCount;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Folder f = folders.get(position);
                // We can access the data within the views
                Toast.makeText(v.getContext(), "Folder ID: "+f.getId(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
