package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StopWater extends AppCompatActivity {

    private EditText editTextNumberSigned2q2;
    private TextView textView3ww;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "StopWaterPrefs";
    private static final String KEY_NUMBER = "savedNumber2q2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_water);

        editTextNumberSigned2q2 = findViewById(R.id.editTextNumberSigned2q2);
        textView3ww = findViewById(R.id.textView3ww);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Восстановление сохраненного числа после перезапуска
        String savedNumber = sharedPreferences.getString(KEY_NUMBER, "");
        editTextNumberSigned2q2.setText(savedNumber);

        // Слушатель для сохранения числа при изменении текста в editTextNumberSigned2q2
        editTextNumberSigned2q2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Сохраняем введенное число в SharedPreferences
                String enteredText = charSequence.toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NUMBER, enteredText);
                editor.apply();

                // Обновляем textView3ww при изменении текста в editTextNumberSigned2q2
                updateTextView3ww(enteredText);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    // Метод для обновления textView3ww в зависимости от сравнения значений
    private void updateTextView3ww(String enteredText) {
        int valueFromEditText = Integer.parseInt(enteredText);
        int valueFromMainActivity = getIntent().getIntExtra("numericValueFromMainActivity", 0);

        // Вычисляем значение для сравнения
        int comparisonValue = valueFromEditText + 40000;

        // Сравниваем значения
        if (comparisonValue > valueFromMainActivity) {
            // Если значение из editTextNumberSigned2q2 + 40000 больше значения из MainActivity,
            // вычисляем разницу и выводим в textView3ww
            int difference = comparisonValue - valueFromMainActivity;
            textView3ww.setText(String.valueOf(difference));
        } else {
            // Если значение меньше или равно, выводим "Замените тормозную жидкость"
            textView3ww.setText("Замените тормозную жидкость");
        }
    }
    public void onClickHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
