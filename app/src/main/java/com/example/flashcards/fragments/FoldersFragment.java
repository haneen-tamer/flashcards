package com.example.flashcards.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.adapeters.FolderAdapter;
import com.example.flashcards.models.Folder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class FoldersFragment extends Fragment {

    private RecyclerView rv;
    private ArrayList<Folder> folders;
    private FolderAdapter folderAdapter;
    private DBHelper helper;

    public FoldersFragment() {
        // Required empty public constructor
    }


    public static FoldersFragment newInstance() {
        FoldersFragment fragment = new FoldersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_folders, container, false);
        FloatingActionButton fab = root.findViewById(R.id.folderFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Create folder dialog", Toast.LENGTH_SHORT).show();
            }
        });

        rv = root.findViewById(R.id.folderRecyclerView);
        helper = new DBHelper(getContext());
        folders = helper.getAllFolders();
        folderAdapter = new FolderAdapter(folders);
        rv.setAdapter(folderAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }
}