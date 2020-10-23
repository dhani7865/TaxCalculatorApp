package com.example.gitproject;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textViewIncomeTax;
    EditText Salary;
    Button CalculateIncomeTax;

    // Calling the TaxCalculator class
    tax TaxCalculator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Salary = (EditText) findViewById(R.id.Salary);

        Salary.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    displayOnGo();
                    handled = true;
                }
                return handled;
            }
        });
//        CalculateIncomeTax.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String salary=Salary.getText().toString();
//
//            }
//        });


        TaxCalculator = new tax();
        textViewIncomeTax = (TextView)findViewById(R.id.textViewIncomeTax);
        Salary = (EditText)findViewById(R.id.Salary);
        CalculateIncomeTax = (Button)findViewById(R.id.CalculateIncomeTax);



    }

    public void displayOnGoBtn(View view) {
        displayOnGo();
    }

    public void displayOnGo() {
        if (Salary.getText().length() > 0) {
            int enteredValue = Integer.parseInt(Salary.getText().toString())*100;
            TaxCalculator.setGrossIncome(enteredValue);
            int getAnnualNetIncome=TaxCalculator.calculateTotalTaxDeductions();
//            int getAnnualNetIncome=TaxCalculator.getAnnualNetIncome();
            showValues(getAnnualNetIncome);
        }
    }


    public void showValues(int getAnnualNetIncome) {
        textViewIncomeTax.setVisibility(View.VISIBLE);
        textViewIncomeTax.setText(displayAsPoundsAndPence(getAnnualNetIncome));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public static String displayAsPoundsAndPence(int value) {
        DecimalFormat currency = new DecimalFormat("£#,###,##0.00");
        Double valueDouble = value/100.0;
        return currency.format(valueDouble);
    }
}
