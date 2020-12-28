package com.example.demux;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    QuestionsAdapter questionsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> topics=new ArrayList<>();
        topics.add(0,"Array");
        topics.add("Hash Tables");

        ArrayList<String> company=new ArrayList<>();
        company.add(0,"Google");
        company.add("Facebook");
        company.add("Amazon");

        ArrayList<String> college=new ArrayList<>();
        college.add(0,"IIT delhi");
        college.add("IIT Mumabai");
        college.add("MNIT Jaipur");


        ArrayList<QuestionsItems> questionsItems=new ArrayList<>();
        questionsItems.add(new QuestionsItems("Two SUM","Easy",true,"Internship",
                "Online","ahahahahahhahahahahahaha",20,topics,company,college));
        questionsItems.add(new QuestionsItems("Factorial","Hard",true,"Internship",
                "Online","ahahahahahhahahahahahaha",20,topics,company,college));
        questionsItems.add(new QuestionsItems("Middle Element","Medium",true,"Internship",
                "Online","ahahahahahhahahahahahaha",20,topics,company,college));

        recyclerView=findViewById(R.id.MainRecyclerView);
        questionsAdapter=new QuestionsAdapter(questionsItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(questionsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.search_menu,menu);
        MenuItem searchItem= menu.findItem(R.id.searchView);
        SearchView searchView= (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                questionsAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}