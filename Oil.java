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



public class Oil extends AppCompatActivity {

    private EditText editTextNumberOil;
    private TextView textViewResultOil;
    private SharedPreferences sharedPreferencesOil;

    private static final String PREFS_NAME_Oil = "MyPrefs2";
    private static final String KEY_NUMBER_Oil = "savedNumber2";

    // Добавлен ключ из первой активности
    private static final String MainNumber = "savedNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil);

        editTextNumberOil = findViewById(R.id.editTextNumberSigned2);
        textViewResultOil = findViewById(R.id.textView3w);

        sharedPreferencesOil = getSharedPreferences(PREFS_NAME_Oil, MODE_PRIVATE);

        // Восстановление сохраненного числа после перезапуска
        String savedNumber = sharedPreferencesOil.getString(KEY_NUMBER_Oil, "");
        editTextNumberOil.setText(savedNumber);

        // Получаем значение из первой активности
        int numericValueFromFirstActivity = getIntent().getIntExtra("numericValue", 0);

        // Слушатель для сохранения числа при изменении текста в EditText
        editTextNumberOil.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Сохраняем введенное число в SharedPreferences
                String enteredText = charSequence.toString();
                SharedPreferences.Editor editor = sharedPreferencesOil.edit();
                editor.putString(KEY_NUMBER_Oil, enteredText);
                editor.apply();

                // Обновляем результат и выводим в textViewResult
                displayComparisonResultOil(numericValueFromFirstActivity); // Передаем значение из первой активности
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Вызываем метод при создании активити, чтобы отобразить начальные значения
        displayComparisonResultOil(numericValueFromFirstActivity);
    }

    // Метод для сравнения значений и отображения результата в textViewResult
    private void displayComparisonResultOil(int numericValueFromFirstActivity) {
        // Получаем значения из sharedPreferencesOil
        String value2 = sharedPreferencesOil.getString(KEY_NUMBER_Oil, "Default2");

        // Преобразуем значения в целочисленный формат
        int value1Int = numericValueFromFirstActivity;
        int value2Int = Integer.parseInt(value2) + 10000;

        // Сравниваем значения
        if (value1Int < value2Int) {
            // Если первое значение меньше второго, вычисляем разницу и выводим в textViewResult
            int difference = value2Int - value1Int;
            textViewResultOil.setText(String.valueOf(difference));
        } else if (value1Int >= value2Int) {
            // Если первое значение больше либо равно второму, выводим надпись "Замените масло"
            textViewResultOil.setText("Замените масло");
        }
    }

    public void onClickHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickGoToMessages(View view) {
        Intent intent = new Intent(this, Messages.class);
        String oilText = textViewResultOil.getText().toString();
        intent.putExtra("oilText", oilText);
        startActivity(intent);
    }
}


/*class OilMarc extends AppCompatActivity {

    private EditText editTextNumberOil;
    private TextView textViewResultOil;
    private SharedPreferences sharedPreferencesOil;

    private static final String PREFS_NAME_OilName = "MyPrefs3";
    private static final String KEY_NUMBER_OilName = "savedNumber3";

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil_marc);

        editTextNumberOil = findViewById(R.id.editTextText);
        textViewResultOil = findViewById(R.id.editTextText);

        sharedPreferencesOil = getSharedPreferences(PREFS_NAME_OilName, MODE_PRIVATE);

        // Восстановление сохраненного числа после перезапуска
        String savedNumber = sharedPreferencesOil.getString(KEY_NUMBER_OilName, "");
        editTextNumberOil.setText(savedNumber);

        // Слушатель для сохранения числа при изменении текста в EditText
        editTextNumberOil.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Сохраняем введенное число в SharedPreferences
                String enteredText = charSequence.toString();
                SharedPreferences.Editor editor = sharedPreferencesOil.edit();
                editor.putString(KEY_NUMBER_OilName, enteredText);
                editor.apply();

                // Обновляем результат и выводим в textViewResult
                textViewResultOil.setText(enteredText);
            }


            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}*/
