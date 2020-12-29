package com.example.demux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView fullquestion,college,company,difficulty,interviewtype,jobnature,topic,trending;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        fullquestion=findViewById(R.id.details_fullquestion);
        college=findViewById(R.id.details_collegeDetails);
        company=findViewById(R.id.details_companyDetails);
        difficulty=findViewById(R.id.details_DiffcultyDetails);
        interviewtype=findViewById(R.id.details_interviewTypeDetails);
        jobnature=findViewById(R.id.details_jobNatureDetails);
        topic=findViewById(R.id.details_topicDetails);
        trending=findViewById(R.id.details_trendingDetails);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        fullquestion.setText(getIntent().getStringExtra("fullquestion"));
        college.setText(getIntent().getStringExtra("college"));
        company.setText(getIntent().getStringExtra("company"));
        difficulty.setText(getIntent().getStringExtra("difficulty"));
        interviewtype.setText(getIntent().getStringExtra("interviewtype"));
        jobnature.setText(getIntent().getStringExtra("jobnature"));
        topic.setText(getIntent().getStringExtra("topic"));
        trending.setText(getIntent().getStringExtra("trending"));
    }

    public void back(View view) {
        Intent intent=new Intent(DetailsActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}