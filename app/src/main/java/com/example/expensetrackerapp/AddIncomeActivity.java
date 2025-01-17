package com.example.expensetrackerapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddIncomeActivity extends AppCompatActivity {

    private EditText amountEditText;
    private EditText detailEditText;
    private Button saveButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_income);

        dbHelper = new DatabaseHelper(this);

        amountEditText = findViewById(R.id.amountEditText);
        detailEditText = findViewById(R.id.detailEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> saveIncome());
    }

    private void saveIncome() {
        String amountStr = amountEditText.getText().toString();
        String detail = detailEditText.getText().toString();

        if (amountStr.isEmpty() || detail.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        long result = dbHelper.addIncome(amount, detail);

        if (result != -1) {
            Toast.makeText(this, "Income saved", Toast.LENGTH_SHORT).show();
            finish(); // Close this activity and return to the previous one (MainActivity)
        } else {
            Toast.makeText(this, "Error saving income", Toast.LENGTH_SHORT).show();
        }
    }
}
