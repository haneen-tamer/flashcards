package com.example.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.fragments.CustomDialog;
import com.example.flashcards.models.Deck;
import com.example.flashcards.utils.QuizController;

public class QuizActivity extends AppCompatActivity {
    Deck d;
    QuizController quizController;
    TextView defTV;
    EditText termET;
    DBHelper helper;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        termET=findViewById(R.id.termQuizET);
        defTV= findViewById(R.id.defQuizTV);
        helper = new DBHelper(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            d = (Deck) extras.getSerializable("deck");
            quizController = new QuizController(d.getCards(), helper);
            defTV.setText(quizController.getNextDef());

            termET.setOnEditorActionListener((v, actionId, event) -> {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    quizController.checkAnswer(v.getText().toString());
                    if(quizController.isDone())
                        displayResult();
                    else{
                        v.setText("");
                        v.clearFocus();
                        defTV.setText(quizController.getNextDef());
                        v.requestFocus();
                    }
                    return true;
                }
                return false;
            });
        }
    }

    public void displayResult(){
        CustomDialog dialog = new CustomDialog();
        dialog.count= quizController.getCorrectCount();
        dialog.show(getSupportFragmentManager(), "quiz");
    }
}