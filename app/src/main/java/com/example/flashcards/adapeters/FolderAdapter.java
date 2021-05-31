package com.example.flashcards.adapeters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView deckCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.folderTitleCardTV);
            deckCount = itemView.findViewById(R.id.folderDeckCountCardTV);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDeckCount() {
            return deckCount;
        }
    }
}
