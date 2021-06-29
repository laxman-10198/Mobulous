package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.Adapter.BookMarkAdapter;
import com.example.myapplication.LocalDataBase.SQLiteDataBase;
import com.example.myapplication.Model.RestaurantsDetails;
import com.example.myapplication.R;

import java.util.List;


public class BookMarkActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SQLiteDataBase db ;
    List<RestaurantsDetails> contacts;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_mark);
        recyclerView=findViewById(R.id.rv_bookmark);
        textView=findViewById(R.id.error2);
        db = new SQLiteDataBase(this);
        contacts = db.getAllContacts();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        if (contacts.isEmpty()){
            textView.setText("Not Data Found");
        }else 
        recyclerView.setAdapter(new BookMarkAdapter(getApplicationContext(),contacts));
        textView.setText("");
        }
    }