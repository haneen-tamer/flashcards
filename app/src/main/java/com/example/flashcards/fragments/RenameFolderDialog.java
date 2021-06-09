package com.example.flashcards.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.models.Folder;

public class RenameFolderDialog extends DialogFragment {

    EditText titleET;
    Button cancel;
    Button ok;
    DBHelper helper;
    Folder f;

    public RenameFolderDialog(Folder f) {
        this.f = f;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rename_folder_popup, container, false);
        titleET = v.findViewById(R.id.folderTitleETRename);
        cancel = v.findViewById(R.id.cancelBtnRename);
        ok = v.findViewById(R.id.okBtnRename);
        helper = new DBHelper(getContext());
        titleET.setText(f.getTitle());
        cancel.setOnClickListener(v1 -> {
            getDialog().dismiss();
        });
        ok.setOnClickListener(v1 -> {
            f.setTitle(titleET.getText().toString());
            helper.renameFolder(f);
            getDialog().dismiss();
        });


        return v;

    }
}
