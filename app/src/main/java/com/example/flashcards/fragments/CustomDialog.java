package com.example.flashcards.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.example.flashcards.R;
import com.example.flashcards.activities.LearnActivity;
import com.example.flashcards.activities.MainActivity;

public class CustomDialog extends DialogFragment {

    Button back;
    Button tryagain;
    TextView learnres;
    ProgressBar progb;
    Toolbar toolbar;

    public int count;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.learn_result_popup, container, false);
        back= (Button) view.findViewById(R.id.button2);
        tryagain= (Button) view.findViewById(R.id.button3);
        //toolbar = (Toolbar) view.findViewById(R.id.viewDeckToolbar2);
        learnres= (TextView) view.findViewById(R.id.CorrectAnswersCount);

        learnres.setText(""+count);

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getActivity().onBackPressed();

            }
        });
        tryagain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent i = new Intent(getContext(), LearnActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

}
