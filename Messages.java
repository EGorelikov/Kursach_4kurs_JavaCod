package com.example.myapplication;

import static com.example.myapplication.MainActivity.MainNumber;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

public class Messages extends AppCompatActivity {

    private TextView textViewOilText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        textViewOilText = findViewById(R.id.textViewOilText);

        String oilText = getIntent().getStringExtra("oilText");

        textViewOilText.setText(oilText);
    }
}

