package com.example.expensetrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView totalExpenseTextView;
    private TextView totalIncomeTextView;
    private ListView expenseListView;
    private ListView incomeListView;
    private Button addExpenseButton;
    private Button addIncomeButton;
    private Button deleteAllExpensesButton;
    private Button deleteAllIncomeButton;
    private ExpenseAdapter expenseAdapter;
    private IncomeAdapter incomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        totalExpenseTextView = findViewById(R.id.totalExpenseTextView);
        totalIncomeTextView = findViewById(R.id.totalIncomeTextView);
        expenseListView = findViewById(R.id.expenseListView);
        incomeListView = findViewById(R.id.incomeListView);
        addExpenseButton = findViewById(R.id.addExpenseButton);
        addIncomeButton = findViewById(R.id.addIncomeButton);
        deleteAllExpensesButton = findViewById(R.id.deleteAllExpensesButton);
        deleteAllIncomeButton = findViewById(R.id.deleteAllIncomeButton);

        addExpenseButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
            startActivity(intent);
        });

        addIncomeButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddIncomeActivity.class);
            startActivity(intent);
        });

        deleteAllExpensesButton.setOnClickListener(v -> {
            dbHelper.deleteAllExpenses();
            updateLists();
            updateTotalValues();
        });

        deleteAllIncomeButton.setOnClickListener(v -> {
            dbHelper.deleteAllIncome();
            updateLists();
            updateTotalValues();
        });

        expenseAdapter = new ExpenseAdapter(this, dbHelper.getAllExpenses());
        expenseListView.setAdapter(expenseAdapter);

        incomeAdapter = new IncomeAdapter(this, dbHelper.getAllIncome());
        incomeListView.setAdapter(incomeAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTotalValues();
        updateLists();
    }

    private void updateTotalValues() {
        double totalExpense = dbHelper.getTotalExpense();
        double totalIncome = dbHelper.getTotalIncome();

        totalExpenseTextView.setText(String.format("Total Expense: ₹%.2f", totalExpense));
        totalIncomeTextView.setText(String.format("Total Income: ₹%.2f", totalIncome));
    }

    private void updateLists() {
        List<Expense> expenses = dbHelper.getAllExpenses();
        List<Income> incomes = dbHelper.getAllIncome();

        expenseAdapter.updateExpenses(expenses);
        incomeAdapter.updateIncome(incomes);
    }
}
