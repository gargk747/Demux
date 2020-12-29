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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
    private String difficulty;
    private String company;
    private String college;
    private String topic;
    private String trending;
    private String jobNature;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=findViewById(R.id.MainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                    bottomSheetDialog.setCanceledOnTouchOutside(false);
                    bottomSheetDialog.show();

                    Spinner difficultySpinner= sheetView.findViewById(R.id.spinner1);
                    ArrayAdapter<CharSequence> adapter1= ArrayAdapter.createFromResource(getApplicationContext(),R.array.Difficulty,android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    difficultySpinner.setAdapter(adapter1);
                    difficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            difficulty =parent.getItemAtPosition(position).toString();
                            questionsAdapter.getFilter().filter(difficulty);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    Spinner companiesSpinner= sheetView.findViewById(R.id.spinner2);
                    ArrayAdapter<CharSequence> adapter2= ArrayAdapter.createFromResource(getApplicationContext(),R.array.Companies,android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    companiesSpinner.setAdapter(adapter2);
                    companiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            company =parent.getItemAtPosition(position).toString();
                            questionsAdapter.getFilter().filter(company);
                            //bottomSheetDialog.cancel();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    Spinner collegeSpinner= sheetView.findViewById(R.id.spinner3);
                    ArrayAdapter<CharSequence> adapter3= ArrayAdapter.createFromResource(getApplicationContext(),R.array.College,android.R.layout.simple_spinner_item);
                    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    collegeSpinner.setAdapter(adapter3);
                    collegeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            college =parent.getItemAtPosition(position).toString();
                            questionsAdapter.getFilter().filter(college);
                            //bottomSheetDialog.cancel();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    Spinner topicSpinner= sheetView.findViewById(R.id.spinner4);
                    ArrayAdapter<CharSequence> adapter4= ArrayAdapter.createFromResource(getApplicationContext(),R.array.Topic,android.R.layout.simple_spinner_item);
                    adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    topicSpinner.setAdapter(adapter4);
                    topicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            topic =parent.getItemAtPosition(position).toString();
                            questionsAdapter.getFilter().filter(topic);
                            //bottomSheetDialog.cancel();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    Spinner jobNatureSpinner= sheetView.findViewById(R.id.spinner5);
                    ArrayAdapter<CharSequence> adapter5= ArrayAdapter.createFromResource(getApplicationContext(),R.array.JobNature,android.R.layout.simple_spinner_item);
                    adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    jobNatureSpinner.setAdapter(adapter5);
                    jobNatureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            jobNature =parent.getItemAtPosition(position).toString();
                            questionsAdapter.getFilter().filter(jobNature);
                            //bottomSheetDialog.cancel();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    Spinner trendingSpinner= sheetView.findViewById(R.id.spinner6);
                    ArrayAdapter<CharSequence> adapter6= ArrayAdapter.createFromResource(getApplicationContext(),R.array.Trending,android.R.layout.simple_spinner_item);
                    adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    trendingSpinner.setAdapter(adapter6);
                    trendingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            trending =parent.getItemAtPosition(position).toString();
                            questionsAdapter.getFilter().filter(trending);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    Button clear=sheetView.findViewById(R.id.clear);
                    clear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            questionsAdapter.getFilter().filter(null);
                            Toast.makeText(MainActivity.this,"All filters cleared",Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.cancel();
                        }
                    });

                    bottomSheetDialog.show();
                }
                return false;
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