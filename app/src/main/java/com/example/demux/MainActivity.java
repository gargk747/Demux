package com.example.demux;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    QuestionsAdapter questionsAdapter;
    ArrayList<QuestionsItems> questionsItems;
    BottomSheetDialog bottomSheetDialog;
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


//        questionsItems.add(new QuestionsItems("Two SUM","Easy",true,"Internship",
//                "Online","ahahahahahhahahahahahaha",20,topics,company,college));
//        questionsItems.add(new QuestionsItems("Factorial","Hard",true,"Internship",
//                "Online","ahahahahahhahahahahahaha",20,topics,company,college));
//        questionsItems.add(new QuestionsItems("Middle Element","Medium",true,"Internship",
//                "Online","ahahahahahhahahahahahaha",20,topics,company,college));

        recyclerView=findViewById(R.id.MainRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        questionsAdapter=new QuestionsAdapter(questionsItems);
//        recyclerView.setAdapter(questionsAdapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Questions");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                questionsItems = new ArrayList<QuestionsItems>();
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {

                    QuestionsItems q=dataSnapshot.getValue(QuestionsItems.class);
                    questionsItems.add(q);
                }
                questionsAdapter=new QuestionsAdapter(questionsItems);
                recyclerView.setAdapter(questionsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"Opps",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.tool_menu,menu);
        MenuItem searchItem= menu.findItem(R.id.searchView);
        MenuItem filterItem=menu.findItem(R.id.filterView);
        bottomSheetDialog= new BottomSheetDialog(MainActivity.this, R.style.BottomSheetTheme);
        filterItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getTitle().toString().equals("Filter")){
                    View sheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.filter_bottom_dialog,(ViewGroup) findViewById(R.id.filter_sheet));
                    bottomSheetDialog.setContentView(sheetView);
                    bottomSheetDialog.show();
                }
                return true;
            }
        });
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