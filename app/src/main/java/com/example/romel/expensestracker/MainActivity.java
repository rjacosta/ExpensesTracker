package com.example.romel.expensestracker;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Button enterButton;
    private Spinner expensesSpinner;
    private String[] expenseTypes = {"Previous Month", "Dad", "Food", "Transportation", "Work", "Misc"};
    private EditText enterAmountText;
    private TextView netExpenseText;
    private Totals totals;

    private TextView[] expenseTotals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterButton = findViewById(R.id.enter_button);

        expensesSpinner = findViewById(R.id.expenses_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, expenseTypes);
        expensesSpinner.setAdapter(adapter);

        enterAmountText = findViewById(R.id.enter_amount_text);
        netExpenseText = findViewById(R.id.net_expense_text);

        expenseTotals = new TextView[6];
        expenseTotals[0] = findViewById(R.id.expense_prev_month);
        expenseTotals[1] = findViewById(R.id.expense_dad);
        expenseTotals[2] = findViewById(R.id.expense_food);
        expenseTotals[3] = findViewById(R.id.expense_transportation);
        expenseTotals[4] = findViewById(R.id.expense_work);
        expenseTotals[5] = findViewById(R.id.expense_misc);

        totals = new Totals();

        enterButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                if (enterAmountText.getText().length() != 0) {
                    double expenseAmt = Double.parseDouble(enterAmountText.getText().toString());
                    String expenseType = expensesSpinner.getSelectedItem().toString();
                    totals.setExpense(expenseType, expenseAmt, false);
                    String expenseText;
                    switch (expenseType) {
                        case "Previous Month":
                            expenseText = String.format("Previous Month: %.2f", totals.getPrevMonth());
                            expenseTotals[0].setText(expenseText);
                            break;
                        case "Dad":
                            expenseText = String.format("Dad: %.2f", totals.getDad());
                            expenseTotals[1].setText(expenseText);
                            break;
                        case "Food":
                            expenseText = String.format("Food: %.2f", totals.getFood());
                            expenseTotals[2].setText(expenseText);
                            break;
                        case "Transportation":
                            expenseText = String.format("Transportation: %.2f", totals.getTransportation());
                            expenseTotals[3].setText(expenseText);
                            break;
                        case "Work":
                            expenseText = String.format("Work: %.2f", totals.getWork());
                            expenseTotals[4].setText(expenseText);
                            break;
                        case "Misc":
                            expenseText = String.format("Misc: %.2f", totals.getMisc());
                            expenseTotals[5].setText(expenseText);
                            break;
                    }
                    expenseText = String.format("Total Expenses: %.2f", totals.getNetExpenses());
                    netExpenseText.setText(expenseText);
                }
            }
        });

    }

}

