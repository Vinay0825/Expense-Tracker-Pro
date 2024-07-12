package com.example.expensetrackerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ExpenseAdapter extends ArrayAdapter<Expense> {

    private Context context;
    private List<Expense> expenseList;

    public ExpenseAdapter(@NonNull Context context, List<Expense> expenseList) {
        super(context, 0, expenseList);
        this.context = context;
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_record, parent, false);
        }

        Expense expense = expenseList.get(position);

        TextView amountTextView = convertView.findViewById(R.id.amountTextView);
        TextView detailTextView = convertView.findViewById(R.id.detailTextView);
        TextView timestampTextView = convertView.findViewById(R.id.timestampTextView);

        amountTextView.setText(String.valueOf(expense.getAmount()));
        detailTextView.setText(expense.getDescription());
        timestampTextView.setText(expense.getTimestamp());

        return convertView;
    }

    public void updateExpenses(List<Expense> newExpenseList) {
        expenseList.clear();
        expenseList.addAll(newExpenseList);
        notifyDataSetChanged();
    }
}
