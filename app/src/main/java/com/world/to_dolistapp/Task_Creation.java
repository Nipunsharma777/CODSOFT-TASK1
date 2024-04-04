package com.world.to_dolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Task_Creation extends AppCompatActivity {

    TextView selectedDateTextView;
    Button button2;
    Spinner categorySpinner;
    Spinner categorySpinner2;
    CheckBox checkBox;
    CheckBox checkBox2;
    EditText editText1;
    Button button1;
    String dat;
    String priority;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);

        categorySpinner = findViewById(R.id.categorySpinner);
        categorySpinner2 = findViewById(R.id.categorySpinner2);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        selectedDateTextView = findViewById(R.id.selectedDateTextView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        editText1 = findViewById(R.id.editText1);

        sharedPreferences = getSharedPreferences("task_details", MODE_PRIVATE);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskName = editText1.getText().toString();
                String dueDate = selectedDateTextView.getText().toString();
                String priority = categorySpinner2.getSelectedItem().toString();

                int taskCounter = sharedPreferences.getInt("task_counter", 0);

                int taskNumber = taskCounter + 1;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("task_" + taskNumber + "_task_name", taskName);
                editor.putString("task_" + taskNumber + "_due_date", dueDate);
                editor.putString("task_" + taskNumber + "_priority", priority);
                editor.putInt("task_counter", taskNumber); // Update task counter
                editor.apply();

                Intent intent = new Intent(Task_Creation.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        List<String> categories = new ArrayList<>();
        categories.add("Default");
        categories.add("Work");
        categories.add("Personal");
        categories.add("Study");
        categories.add("Others");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner.setAdapter(spinnerArrayAdapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedCategory = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> categories2 = new ArrayList<>();
        categories2.add("No Priority");
        categories2.add("Low");
        categories2.add("Medium");
        categories2.add("High");
        categories2.add("Urgent");

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories2);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner2.setAdapter(spinnerArrayAdapter2);

        categorySpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                priority = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {

                }  else {

                }

            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {

                }  else {

                }

            }
        });

    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        selectedDateTextView.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        dat = dayOfMonth + "/" + (month + 1) + "/" + year;

                    }
                },
                year, month, dayOfMonth);

        datePickerDialog.show();
    }

}