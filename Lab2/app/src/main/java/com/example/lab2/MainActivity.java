package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityEditText;
    Button addCityButton, deleteCityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View variables
        cityList = findViewById(R.id.city_list);
        cityEditText = findViewById(R.id.cityEditText);
        addCityButton = findViewById(R.id.addCityButton);
        deleteCityButton = findViewById(R.id.deleteCityButton);

        // Cities list
        dataList = new ArrayList<>();  // store the entered city names

        // Initialize the ArrayAdapter
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, dataList);  // used the built in layout (*Source referenced in references)
        cityList.setAdapter(cityAdapter);
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Add city button
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityEditText.getText().toString().trim();
                if (!cityName.isEmpty()) {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged();
                    cityEditText.setText("");
                    Toast.makeText(MainActivity.this, "City added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Enter a city name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Delete city button
        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = cityList.getCheckedItemPosition();
                if (position != ListView.INVALID_POSITION) {
                    dataList.remove(position);
                    cityAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "City deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Select a city to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}