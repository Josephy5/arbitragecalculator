package com.example.arbitragecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ArbitrageCalculator extends AppCompatActivity {

    Button calcButton;
    EditText AOutcome1Edit, AOutcome2Edit, BOutcome1Edit, BOutcome2Edit;
    double AOutcome1, AOutcome2, BOutcome1, BOutcome2;

    int BookMarkerOutcome1Index, BookMarkerOutcome2Index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        calcButton = findViewById(R.id.CalculateButton);
        AOutcome1Edit = findViewById(R.id.AOutcome1);
        AOutcome2Edit = findViewById(R.id.AOutcome2);
        BOutcome1Edit = findViewById(R.id.BOutcome1);
        BOutcome2Edit = findViewById(R.id.BOutcome2);
    }

    private boolean calculateStuff(double AOutcome1, double AOutcome2, double BOutcome1, double BOutcome2) {
        double[] allOutcome1 = {AOutcome1, BOutcome1};
        double[] allOutcome2 = {AOutcome2, BOutcome2};

        boolean result = false;
        for (int x = 0; x < allOutcome1.length; x++) {
            for (int y = 0; y < allOutcome1.length; y++) {
                if (((1 / allOutcome1[x]) + (1 / allOutcome2[y])) < 1) {
                    result = true;
                    BookMarkerOutcome1Index = x;
                    BookMarkerOutcome2Index = y;
                }
            }
        }
        return result;
    }

    private void showNoArbDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false).setTitle("No Arbitrage Found").setNeutralButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }

    private void showArbDialog(int Outcome1, int Outcome2) {
        String mes = "";
        switch (Outcome1) {
            case 0:
                mes += "Best bookmarker for outcome 1 is Bookmarker 1\n";
                break;
            case 1:
                mes += "Best bookmarker for outcome 1 is Bookmarker 2\n";
                break;
        }
        switch (Outcome2) {
            case 0:
                mes += "Best bookmarker for outcome 2 is Bookmarker 1\n";
                break;
            case 1:
                mes += "Best bookmarker for outcome 2 is Bookmarker 2\n";
                break;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false).setTitle("Arbitrage Found").setMessage(mes).setNeutralButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }

    public void clickCalculate(View view) {
        BookMarkerOutcome1Index = 0;
        BookMarkerOutcome2Index = 0;
        boolean foundArbitrage;

        try {
            AOutcome1 = Integer.parseInt(AOutcome1Edit.getText().toString());
            AOutcome2 = Integer.parseInt(AOutcome2Edit.getText().toString());
            BOutcome1 = Integer.parseInt(BOutcome1Edit.getText().toString());
            BOutcome2 = Integer.parseInt(BOutcome2Edit.getText().toString());

            foundArbitrage = calculateStuff(AOutcome1, AOutcome2, BOutcome1, BOutcome2);

            if (foundArbitrage) {
                showArbDialog(BookMarkerOutcome1Index, BookMarkerOutcome2Index);
            } else {
                showNoArbDialog();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid Number inputted", Toast.LENGTH_LONG).show();
        }
    }
}