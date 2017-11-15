package com.example.romel.expensestracker;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private Button enterButton;
    private Spinner expensesSpinner;
    private String[] expenseTypes = {"Dad", "Food", "Misc", "Previous Month", "Transportation", "Work"};
    private EditText enterAmountText;
    private TextView netExpenseText;
    private Totals totals;

    private TextView[] expenseTotalsTextViews;

    private FirebaseDatabase fdb;
    private DatabaseReference myRef;
    private boolean getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpDatabase();

        enterButton = findViewById(R.id.enter_button);

        expensesSpinner = findViewById(R.id.expenses_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, expenseTypes);
        expensesSpinner.setAdapter(adapter);

        enterAmountText = findViewById(R.id.enter_amount_text);
        netExpenseText = findViewById(R.id.net_expense_text);

        expenseTotalsTextViews = new TextView[6];
        expenseTotalsTextViews[0] = findViewById(R.id.expense_dad);
        expenseTotalsTextViews[1] = findViewById(R.id.expense_food);
        expenseTotalsTextViews[2] = findViewById(R.id.expense_misc);
        expenseTotalsTextViews[3] = findViewById(R.id.expense_prev_month);
        expenseTotalsTextViews[4] = findViewById(R.id.expense_transportation);
        expenseTotalsTextViews[5] = findViewById(R.id.expense_work);

        totals = new Totals();

        enterButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                if (enterAmountText.getText().length() != 0) {
                    //DecimalFormat dFormat = new DecimalFormat("#.00");
                    formatter.setMaximumFractionDigits(3);
                    double expenseAmt = Double.parseDouble(enterAmountText.getText().toString());
                    String eA3 = formatter.format(expenseAmt);
                    formatter.setMaximumFractionDigits(2);
                    String eA2 = formatter.format(expenseAmt);
                    double formattedA3 = Double.parseDouble(eA3.substring(1));
                    double formattedA2 = Double.parseDouble(eA2.substring(1));
                    if (formattedA2 > formattedA3) expenseAmt = formattedA2 - .01;

                    String expenseType = expensesSpinner.getSelectedItem().toString();
                    totals.setExpense(expenseType, expenseAmt, false);
                    updateExpenseText(expenseType);
                }
            }
        });

    }

    public void setUpDatabase() {

        fdb = FirebaseDatabase.getInstance("https://expensestracker-9f64a.firebaseio.com/");
        myRef = fdb.getReferenceFromUrl("https://expensestracker-9f64a.firebaseio.com/");
        getData = false;
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild("Expense Types")) {
                    Log.i("Prepping DB", "Prepping DB");
                    myRef.child("Expense Types").child("Dad").setValue(0);
                    myRef.child("Expense Types").child("Food").setValue(0);
                    myRef.child("Expense Types").child("Misc").setValue(0);
                    myRef.child("Expense Types").child("Previous Month").setValue(0);
                    myRef.child("Expense Types").child("Transportation").setValue(0);
                    myRef.child("Expense Types").child("Work").setValue(0);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getDetails());
            }
        });
        Log.i("Getting Data", "Getting Data");
        myRef.child("Expense Types").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot s: dataSnapshot.getChildren()) {
                    try {
                        totals.setExpense(expenseTypes[i], Double.parseDouble(s.getValue().toString()), true);
                    } catch (NullPointerException e) {}
                    //Log.i("DOUBLE VALUE", "" + Double.parseDouble(s.getValue().toString()));
                    updateExpenseText(expenseTypes[i++]);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @SuppressLint("DefaultLocale")
    public void updateExpenseText(String expenseType) {
        String expenseText;
        myRef = fdb.getReferenceFromUrl("https://expensestracker-9f64a.firebaseio.com/");

        switch (expenseType) {
            case "Dad":
                expenseText = String.format("Dad: %.2f", totals.getDad());
                expenseTotalsTextViews[0].setText(expenseText);
                myRef.child("Expense Types").child("Dad").setValue(totals.getDad());
                break;
            case "Food":
                expenseText = String.format("Food: %.2f", totals.getFood());
                expenseTotalsTextViews[1].setText(expenseText);
                myRef.child("Expense Types").child("Food").setValue(totals.getFood());
                break;
            case "Misc":
                expenseText = String.format("Misc: %.2f", totals.getMisc());
                expenseTotalsTextViews[2].setText(expenseText);
                myRef.child("Expense Types").child("Misc").setValue(totals.getMisc());
                break;
            case "Previous Month":
                expenseText = String.format("Previous Month: %.2f", totals.getPrevMonth());
                expenseTotalsTextViews[3].setText(expenseText);
                myRef.child("Expense Types").child("Previous Month").setValue(totals.getPrevMonth());
                break;
            case "Transportation":
                expenseText = String.format("Transportation: %.2f", totals.getTransportation());
                expenseTotalsTextViews[4].setText(expenseText);
                myRef.child("Expense Types").child("Transportation").setValue(totals.getTransportation());
                Log.i("Transportation Total", totals.getTransportation() + "");
                break;
            case "Work":
                expenseText = String.format("Work: %.2f", totals.getWork());
                expenseTotalsTextViews[5].setText(expenseText);
                myRef.child("Expense Types").child("Work").setValue(totals.getWork());
                break;

        }
        expenseText = String.format("Total Expenses: %.2f", totals.getNetExpenses());
        netExpenseText.setText(expenseText);


    }


}

