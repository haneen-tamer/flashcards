package com.example.flashcards.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.Folder;

import java.util.ArrayList;

public class AddToFolderDialog extends DialogFragment {

    ListView lv;
    DBHelper helper;
    Deck d;

    public AddToFolderDialog(Deck d) {
        this.d = d;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_decktofolder_popup, container, false);
        lv = root.findViewById(R.id.folderTitlesLV);
        helper = new DBHelper(getContext());
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Folder> folders = helper.getAllFolders();
        for(Folder f: folders){
            names.add(f.getTitle());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, names);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            helper.addDeckToFolder(d, folders.get(position));
            getDialog().dismiss();
        });
        return root;
    }

}
