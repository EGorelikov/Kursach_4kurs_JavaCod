package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText MainNumS;
    private EditText editTextNumber;
    private TextView textViewNumber;
    private int numericValue; // Переменная для представления информации в виде цифры
    public SharedPreferences sharedPreferences;
    public static final String MainNumberPREFS = "MainMyPrefs";
    public static final String MainNumber = "MainsavedNumber";

    Button Button_Oil;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainNumS = findViewById(R.id.editTextText2);
        editTextNumber = findViewById(R.id.editTextText2);
        textViewNumber = findViewById(R.id.textView33);

        sharedPreferences = getSharedPreferences(MainNumberPREFS, MODE_PRIVATE);

        // Получаем значение из SharedPreferences
        String savedNumber = sharedPreferences.getString(MainNumber, "");
        editTextNumber.setText(savedNumber);

        // Парсим строку в целое число и устанавливаем значение переменной
        try {
            numericValue = Integer.parseInt(savedNumber);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Обновляем текст в TextView
        textViewNumber.setText(savedNumber);

        // Слушатель для сохранения числа при изменении текста в EditText
        editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Сохраняем введенное число в SharedPreferences
                String enteredText = charSequence.toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(MainNumber, enteredText);
                editor.apply();

                // Обновляем текст в TextView
                textViewNumber.setText(enteredText);

                // Парсим строку в целое число и устанавливаем значение переменной
                try {
                    numericValue = Integer.parseInt(enteredText);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    // Метод для получения значения переменной
    public int getNumericValue() {
        return numericValue;
    }

    // Остальной код активити...

    @Override
    public void onClick(View view) {
        try {
            Intent intent = new Intent(this, Oil.class);
            intent.putExtra("numericValue", getNumericValue()); // Передаем значение переменной в другую активность
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickResetValues(View view) {
        // Создаем интент для запуска второй активности (Anti)
        Intent intent2 = new Intent(this, Oil.class);

        // Очищаем сохраненное значение второй активности в SharedPreferences
        SharedPreferences sharedPreferencesAnti = getSharedPreferences("MyPrefs2", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesAnti.edit();
        editor.remove("savedNumber2");
        editor.apply();

        // Запускаем вторую активность
        startActivity(intent2);
    }

    public void onClick2(View view) {
        try {
            Intent intent = new Intent(this, AntiFreez.class);
            intent.putExtra(MainNumber, getNumericValue()); // Передаем значение переменной в другую активность
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onClickWater(View view) {
        try {
            Intent intent = new Intent(this, Water.class);
            intent.putExtra("numericValue", getNumericValue()); // Передаем значение переменной в другую активность
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onClickStopWater(View view) {
        // Запускаем активити StopWater и передаем в нее значение из MainActivity
        Intent intent = new Intent(this, StopWater.class);
        intent.putExtra("numericValueFromMainActivity", getNumericValue());
        startActivity(intent);

    }

    public void onClickDetales(View view) {
        // Запускаем активити StopWater и передаем в нее значение из MainActivity
        Intent intent = new Intent(this, Detales.class);
        intent.putExtra("numericValueFromMainActivity", getNumericValue());
        startActivity(intent);
    }
    public void onClickMessages(View view) {
        Intent intent = new Intent(this, Messages.class);
        startActivity(intent);
    }

}