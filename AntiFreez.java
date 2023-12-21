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

public class AntiFreez extends AppCompatActivity {

    private EditText editTextNumberAnti;
    private TextView textViewResultAnti;
    private SharedPreferences sharedPreferencesAnti;

    private static final String PREFS_NAME_Anti = "MyPrefsAnti";
    private static final String KEY_NUMBER_Anti = "savedNumberAnti";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_freez);

        editTextNumberAnti = findViewById(R.id.editTextNumberSignedAnti);
        textViewResultAnti = findViewById(R.id.textViewAnti);

        sharedPreferencesAnti = getSharedPreferences(PREFS_NAME_Anti, MODE_PRIVATE);

        // Восстановление сохраненного числа после перезапуска
        String savedNumber = sharedPreferencesAnti.getString(KEY_NUMBER_Anti, "");
        editTextNumberAnti.setText(savedNumber);

        // Получаем значение из первой активности
        int numericValueFromFirstActivity = getIntent().getIntExtra(MainActivity.MainNumber, 0);

        // Слушатель для сохранения числа при изменении текста в EditText
        editTextNumberAnti.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Сохраняем введенное число в SharedPreferences
                String enteredText = charSequence.toString();
                SharedPreferences.Editor editor = sharedPreferencesAnti.edit();
                editor.putString(KEY_NUMBER_Anti, enteredText);
                editor.apply();

                // Обновляем результат и выводим в textViewResult
                displayComparisonResultAnti(numericValueFromFirstActivity); // Передаем значение из первой активности
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Вызываем метод при создании активити, чтобы отобразить начальные значения
        displayComparisonResultAnti(numericValueFromFirstActivity);
    }

    // Метод для сравнения значений и отображения результата в textViewResult
    private void displayComparisonResultAnti(int numericValueFromFirstActivity) {
        // Получаем значения из sharedPreferencesAnti
        String value2 = sharedPreferencesAnti.getString(KEY_NUMBER_Anti, "Default2");

        // Преобразуем значения в целочисленный формат
        int value1Int = numericValueFromFirstActivity;
        int value2Int = Integer.parseInt(value2) + 45000;

        // Сравниваем значения
        if (value1Int < value2Int) {
            // Если первое значение меньше второго, вычисляем разницу и выводим в textViewResultAnti
            int difference = value2Int - value1Int;
            textViewResultAnti.setText(String.valueOf(difference));
        } else if (value1Int >= value2Int) {
            // Если первое значение больше либо равно второму, выводим надпись "Замените масло"
            textViewResultAnti.setText("Замените антифриз");
        }
    }




    public void onClickHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}