package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.flashcards.DataBase.DBHelper;
import com.example.flashcards.R;
import com.example.flashcards.adapeters.ListCardAdapter;
import com.example.flashcards.models.Card;

import java.util.ArrayList;

public class EditDActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rv;;
    CardView card;
    Button Add;
    ListView list;
     DBHelper helper;
    ArrayList<View> array_cards_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_d);

        list =  (ListView) findViewById(R.id.ListView);


        array_cards_list= new ArrayList<View>();
        View v= getLayoutInflater().inflate(R.layout.cardviewlayout,null);
        EditText Term = (EditText) v.findViewById(R.id.Termtext1);
        EditText Definition = (EditText) v.findViewById(R.id.Definitiontext1);
        Card card = new Card(1,Term.getText().toString(),Definition.getText().toString(),1,2,3);

        CardView c= (CardView)v.findViewById(R.id.cardView1);
       array_cards_list.add(v);
        //CardAdaper = new ArrayAdapter<View>(getApplicationContext(), android.R.layout.simple_list_item_1, array_cards_list);




        helper = new DBHelper(getApplicationContext());

        Add = (Button) findViewById(R.id.button);
        Add.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        addView();

    }
    private void addView()
    {
ListCardAdapter adaper = new ListCardAdapter(this,R.layout.cardviewlayout, array_cards_list);
list.setAdapter(adaper);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}