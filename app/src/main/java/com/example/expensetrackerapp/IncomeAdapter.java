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

public class IncomeAdapter extends ArrayAdapter<Income> {

    private Context context;
    private List<Income> incomeList;

    public IncomeAdapter(@NonNull Context context, List<Income> incomeList) {
        super(context, 0, incomeList);
        this.context = context;
        this.incomeList = incomeList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_record, parent, false);
        }

        Income income = incomeList.get(position);

        TextView amountTextView = convertView.findViewById(R.id.amountTextView);
        TextView detailTextView = convertView.findViewById(R.id.detailTextView);
        TextView timestampTextView = convertView.findViewById(R.id.timestampTextView);

        amountTextView.setText(String.valueOf(income.getAmount()));
        detailTextView.setText(income.getDescription());
        timestampTextView.setText(income.getTimestamp());

        return convertView;
    }

    public void updateIncome(List<Income> newIncomeList) {
        incomeList.clear();
        incomeList.addAll(newIncomeList);
        notifyDataSetChanged();
    }
}
