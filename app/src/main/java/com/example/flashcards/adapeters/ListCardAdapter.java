package com.example.flashcards.adapeters;

import android.content.Context;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards.R;
import com.example.flashcards.models.Card;
import com.example.flashcards.models.Deck;


import java.util.ArrayList;


public class ListCardAdapter extends ArrayAdapter<View> {
       private static final String TAG="CardAdapter";
       private Context myContext;
        private ArrayList<View> cards;
int mResource;
        public ListCardAdapter(Context context, int resource, ArrayList<View> objects)
        {
            super(context, resource, objects);
            myContext= context;
        }

       @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
       {



           LayoutInflater inflater= LayoutInflater.from(myContext);
            convertView = inflater.inflate(mResource,parent,false);
           EditText Term = (EditText) convertView.findViewById(R.id.Termtext1);
           EditText Definition = (EditText) convertView.findViewById(R.id.Definitiontext1);

           CardView cardView= (CardView)convertView.findViewById(R.id.cardView1);


           return convertView;
       }
}
