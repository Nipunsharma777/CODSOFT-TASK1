package com.world.to_dolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements TaskAdapter.AdapterCallback, TaskAdapter.AdapterCallback2{

    ImageView imageView3;
    TextView textView3;
    TextView textView2;
    Spinner categorySpinner5;
    RecyclerView recyclerView;
    TaskAdapter taskAdapter;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView3 = findViewById(R.id.imageView3);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        categorySpinner5 = findViewById(R.id.categorySpinner5);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences("task_details", MODE_PRIVATE);
        int taskCounter = sharedPreferences.getInt("task_counter", 0);
        String a = String.valueOf(taskCounter);
        textView3.setText(a);

        List<String> categories = new ArrayList<>();
        categories.add("Select Priority");
        categories.add("Low");
        categories.add("Medium");
        categories.add("High");
        categories.add("Urgent");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner5.setAdapter(spinnerArrayAdapter);

        List<TaskModel> taskList = new ArrayList<>();

        for (int i = 1; i <= taskCounter; i++) {
            String taskName = sharedPreferences.getString("task_" + i + "_task_name", "");
            String dueDate = sharedPreferences.getString("task_" + i + "_due_date", "");
            String priority = sharedPreferences.getString("task_" + i + "_priority", "");

            taskList.add(new TaskModel(taskName, dueDate, priority));
        }

        taskAdapter = new TaskAdapter(taskList);
        taskAdapter.setCallback(this);
        taskAdapter.setCallback2(this);
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.notifyDataSetChanged();

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Task_Creation.class);
                startActivity(intent);

            }
        });

    }

    public void onCheckBoxClicked(boolean isChecked) {

        String a = textView2.getText().toString();
        int value1 = Integer.parseInt(a);
        int p = value1 + 1;
        textView2.setText(String.valueOf(p));

    }

    public void onCheckBoxClicked2(boolean isChecked2) {

        String a = textView2.getText().toString();
        String b = textView3.getText().toString();
        int value1 = Integer.parseInt(a);
        int value2 = Integer.parseInt(b);
        int p = value1 - 1;
        int p2 = value2 - 1;
        textView2.setText(String.valueOf(p));
        textView3.setText(String.valueOf(p2));

    }
}