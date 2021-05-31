package com.example.flashcards.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.flashcards.R;
import com.example.flashcards.adapeters.FolderAdapter;
import com.example.flashcards.models.Folder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoldersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoldersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rv;
    private ArrayList<Folder> folders;
    private FolderAdapter folderAdapter;

    public FoldersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoldersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoldersFragment newInstance(String param1, String param2) {
        FoldersFragment fragment = new FoldersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
        folders = Folder.getFolders();
        folderAdapter = new FolderAdapter(folders);
        rv.setAdapter(folderAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }
}