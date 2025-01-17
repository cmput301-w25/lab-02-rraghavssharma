package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private MyCityAdapter cityAdapter;
    private ArrayList<String> dataList;

    private EditText editTextCity;
    private View bottomLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ListView cityList = findViewById(R.id.city_list);
        Button btnAddCity = findViewById(R.id.btnAddCity);
        Button btnDeleteCity = findViewById(R.id.btnDeleteCity); // if it exists
        Button btnConfirm = findViewById(R.id.btnConfirm);
        editTextCity = findViewById(R.id.editTextCity);
        bottomLayout = findViewById(R.id.bottom_layout);

        bottomLayout.setVisibility(View.GONE);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new MyCityAdapter(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        dataList.add("New City");
        cityAdapter.notifyDataSetChanged();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAddCity.setOnClickListener(v -> bottomLayout.setVisibility(View.VISIBLE));
        btnConfirm.setOnClickListener(v -> {
            String newCity = editTextCity.getText().toString().trim();

            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();

                editTextCity.setText("");
            }

            bottomLayout.setVisibility(View.GONE);
        });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            // Tell the adapter which position is selected
            cityAdapter.setSelectedPosition(position);
        });

        btnDeleteCity.setOnClickListener(v -> {
            // Which item is selected?
            int selectedPos = cityAdapter.getSelectedPosition();
            if (selectedPos != -1) {
                // Remove it from dataList
                dataList.remove(selectedPos);
                // Clear selection
                cityAdapter.setSelectedPosition(-1);
            }
        });

    }
}