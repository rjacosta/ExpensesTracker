package com.example.romel.expensestracker;

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
    private String[] expenseTypes = {"Dad", "Previous Month", "Work", "Food", "Uber", "Misc"};
    private Button expensesButton;
    private EditText enterAmountText;
    private TextView netExpenseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterButton = (Button) findViewById(R.id.enter_button);

        expensesSpinner = (Spinner) findViewById(R.id.expenses_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, expenseTypes);
        expensesSpinner.setAdapter(adapter);

        expensesButton = (Button) findViewById(R.id.expenses_button);
        enterAmountText = (EditText) findViewById(R.id.enter_amount_text);
        netExpenseText = (TextView) findViewById(R.id.net_expense_text);


        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double expenseAmt = Double.parseDouble(enterAmountText.getText().toString());

            }
        });

    }

}

