package com.gmail.adkupenov.chorepower;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private Button mToKidsListButton;
    private Button mToTaskListButton;
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start ListKidsActivity
        mToKidsListButton = (Button) findViewById(R.id.to_kids_button);
        mToKidsListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("mToKidsListButton", "onClick starts");
                Intent intentToKidsList = new Intent(MainActivity.this, ListKidsActivity.class);
                startActivity(intentToKidsList);
                Log.i("mToKidsListButton", "onClick ends");

            }
        });

        // Start ListTasksActivity
        mToTaskListButton = (Button) findViewById(R.id.to_tasks_button);
        mToTaskListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("mToTaskListButton", "onClick starts");
                Intent intentToTaskList = new Intent(MainActivity.this, ListTasksActivity.class);
                startActivity(intentToTaskList);
                Log.i("mToTaskListButton", "onClick ends");
            }
        });


    }
}
