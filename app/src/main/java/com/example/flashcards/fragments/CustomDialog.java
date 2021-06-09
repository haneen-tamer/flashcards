package com.example.flashcards.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.flashcards.R;
import com.example.flashcards.activities.LearnActivity;

public class CustomDialog extends DialogFragment {

    Button back;
    TextView learnres;

    public int count;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.result_popup, container, false);
        back= (Button) view.findViewById(R.id.button2);
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

        return view;
    }

}
