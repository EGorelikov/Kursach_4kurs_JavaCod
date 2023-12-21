package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Detales extends AppCompatActivity {

    private EditText editTextNumber1;
    private EditText editTextNumber2;
    private TextView textViewResult1;
    private TextView textViewResult2;

    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_NUMBER_1 = "savedNumber1";
    private static final String KEY_NUMBER_2 = "savedNumber2";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detales);

        editTextNumber1 = findViewById(R.id.editTextNumberSigned2);
        editTextNumber2 = findViewById(R.id.editTextNumberSigned);
        textViewResult1 = findViewById(R.id.textView3gftw);
        textViewResult2 = findViewById(R.id.textView3w2);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Восстановление сохраненных чисел после перезапуска
        String savedNumber1 = sharedPreferences.getString(KEY_NUMBER_1, "");
        String savedNumber2 = sharedPreferences.getString(KEY_NUMBER_2, "");

        editTextNumber1.setText(savedNumber1);
        editTextNumber2.setText(savedNumber2);

        // Слушатель для сохранения числа при изменении текста в EditText1
        editTextNumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                saveEnteredText(charSequence.toString(), KEY_NUMBER_1);
                displayResult1();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Слушатель для сохранения числа при изменении текста в EditText2
        editTextNumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                saveEnteredText(charSequence.toString(), KEY_NUMBER_2);
                displayResult2();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Вызываем методы при создании активити, чтобы отобразить начальные значения
        displayResult1();
        displayResult2();
    }

    // Метод для сохранения введенного текста в SharedPreferences
    private void saveEnteredText(String enteredText, String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, enteredText);
        editor.apply();
    }

    // Метод для отображения результата в первом текстовом поле
    private void displayResult1() {
        // Получаем значение из sharedPreferences
        String value1 = sharedPreferences.getString(KEY_NUMBER_1, "0");

        // Получаем значение из MainActivity
        int valueFromMainActivity = getIntent().getIntExtra(MainActivity.MainNumber, 0);

        // Суммируем значения и выводим в textViewResult1
        int result = Integer.parseInt(value1) + valueFromMainActivity;
        textViewResult1.setText(String.valueOf(result));
    }

    // Метод для отображения результата во втором текстовом поле
    private void displayResult2() {
        // Получаем значение из sharedPreferences
        String value2 = sharedPreferences.getString(KEY_NUMBER_2, "0");

        // Получаем значение из MainActivity
        int valueFromMainActivity = getIntent().getIntExtra(MainActivity.MainNumber, 0);

        // Суммируем значения и выводим в textViewResult2
        int result = Integer.parseInt(value2) + valueFromMainActivity;
        textViewResult2.setText(String.valueOf(result));
    }

    public void onClickHome(View view) {
        finish(); // Завершаем активити Details и возвращаемся в MainActivity
    }
}
