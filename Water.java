package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Water extends AppCompatActivity {

    private EditText editTextNumberWater;
    private TextView textViewResultWater;
    private SharedPreferences sharedPreferencesWater;

    private static final String PREFS_NAME_Water = "MyPrefsWater";
    private static final String KEY_NUMBER_Water = "savedNumberWater";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        editTextNumberWater = findViewById(R.id.editTextNumberSigned22);
        textViewResultWater = findViewById(R.id.textView34);

        sharedPreferencesWater = getSharedPreferences(PREFS_NAME_Water, MODE_PRIVATE);

        // Восстановление сохраненного числа после перезапуска
        String savedNumber = sharedPreferencesWater.getString(KEY_NUMBER_Water, "");
        editTextNumberWater.setText(savedNumber);

        // Получаем значение из интента
        int numericValueFromFirstActivity = getIntent().getIntExtra("numericValue", 0);

        // Слушатель для сохранения числа при изменении текста в EditText
        editTextNumberWater.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Сохраняем введенное число в SharedPreferences
                String enteredText = charSequence.toString();
                SharedPreferences.Editor editor = sharedPreferencesWater.edit();
                editor.putString(KEY_NUMBER_Water, enteredText);
                editor.apply();

                // Обновляем результат и выводим в textViewResult
                displayComparisonResultWater(numericValueFromFirstActivity);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Вызываем метод при создании активити, чтобы отобразить начальные значения
        displayComparisonResultWater(numericValueFromFirstActivity);
    }

    // Метод для сравнения значений и отображения результата в textViewResult
    private void displayComparisonResultWater(int numericValueFromFirstActivity) {
        // Получаем значения из sharedPreferencesWater
        String value2 = sharedPreferencesWater.getString(KEY_NUMBER_Water, "0");

        // Преобразуем значения в целочисленный формат
        int value1Int = numericValueFromFirstActivity;
        int value2Int = Integer.parseInt(value2) + 15000;


        // Проверяем условия и устанавливаем текст в textViewResultWater
        if (value1Int < value2Int) {
            // Если первое значение меньше второго, вычисляем разницу и выводим в textViewResult
            int difference = value2Int - value1Int;
            textViewResultWater.setText(String.valueOf(difference));
        } else if (value1Int >= value2Int) {
            // Если первое значение больше либо равно второму, выводим надпись "Замените масло"
            textViewResultWater.setText("Замените стеклоомывательную жидкость");
        }
    }
}
