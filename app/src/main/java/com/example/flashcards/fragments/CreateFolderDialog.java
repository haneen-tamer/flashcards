package com.example.flashcards.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.models.Folder;

public class CreateFolderDialog extends DialogFragment {

    EditText titleET;
    Button cancel;
    Button ok;
    DBHelper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_folder_popup, container, false);
        titleET = v.findViewById(R.id.folderTitleET);
        cancel = v.findViewById(R.id.cancelBtnCreateFolder);
        ok = v.findViewById(R.id.okBtnCreateFolder);
        helper = new DBHelper(getContext());

        cancel.setOnClickListener(v1 -> {
            getDialog().dismiss();
        });
        ok.setOnClickListener(v1 -> {
            Folder f = new Folder(helper.getNextFolderID(), titleET.getText().toString());
            helper.AddFolder(f);
            getDialog().dismiss();
        });


        return v;

    }
}
