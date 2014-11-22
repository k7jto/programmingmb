package org.k7jto.bsacalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.widget.TextView;


public class MainActivity extends Activity {

    private int operand;    // switch on this int to determine which math operation

    // These are strings to make the refactoring activity easier
    private String numA;    // Variable to store first number entered
    private String numB;    // Variable to store second number entered
    private String result;  // Variable to store result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

        // set the message to display
        alertbox.setMessage(getString(R.string.app_descrip));

        // add a neutral button to the alert box and assign a click listener
        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

            // click listener on the alert box
            public void onClick(DialogInterface arg0, int arg1) {
                // the button was clicked

            }
        });

        // show it
        alertbox.show();

        return true;


    }


    // This function takes the user's current keystroke and appends it to the
    // numeric display (EditText) at the top
    public void appendNumber(View v) {

        // First, we have to find the EditText from the controls on the page
        EditText myEditText = (EditText)findViewById(R.id.txtDisplay);

        //Next, we need to figure out WHICH button was tapped
        switch (v.getId()) {
            // "1" button was tapped
            case R.id.btn1: myEditText.setText(myEditText.getText() + "1");
                break;
            // "2" button was tapped
            case R.id.btn2: myEditText.setText(myEditText.getText() + "2");
                break;
            // "3" button was tapped
            case R.id.btn3: myEditText.setText(myEditText.getText() + "3");
                break;
            // "4" button was tapped
            case R.id.btn4: myEditText.setText(myEditText.getText() + "4");
                break;
            // "5" button was tapped
            case R.id.btn5: myEditText.setText(myEditText.getText() + "5");
                break;
            // "6" button was tapped
            case R.id.btn6: myEditText.setText(myEditText.getText() + "6");
                break;
            // "7" button was tapped
            case R.id.btn7: myEditText.setText(myEditText.getText() + "7");
                break;
            // "8" button was tapped
            case R.id.btn8: myEditText.setText(myEditText.getText() + "8");
                break;
            // "9" button was tapped
            case R.id.btn9: myEditText.setText(myEditText.getText() + "9");
                break;
            // "0" button was tapped
            case R.id.btn0: myEditText.setText(myEditText.getText() + "0");
                break;
        }
    }

    // If the "C" button is tapped, clear everything
    public void clearDisplay(View v) {
        // Have to get a 'handle' on the EditText button
        EditText myEditText = (EditText)findViewById(R.id.txtDisplay);
        myEditText.setText(""); // Now set the EditText text to "" (empty)
        numA = "";              // Now set numA to empty
        numB = "";              // Now set numB to empty
        result = "";            // And set result to empty
    }



    // This function is called when one of the 4 operation buttons is clicked
    // (add, subtract, mutliple, divide). It clears the EditText field, sets
    // the operand, and enables the "=" button
    public void clickOperand(View v) {
        // Have to get a 'handle' on the EditText button
        EditText myEditText = (EditText)findViewById(R.id.txtDisplay);

        // In casting, it's a good idea to be careful and wrap everything in a
        // try... catch statement
        try {
            numA = myEditText.getText().toString(); // Getting text and putting it into numA
            myEditText.setText("");                 // Clear edit text

            // Now set the operand based on which button was clicked
            switch (v.getId()) {
                case R.id.btnAdd: operand = 1;
                    break;
                case R.id.btnSubtract: operand = 2;
                    break;
                case R.id.btnMultiply: operand = 3;
                    break;
                case R.id.btnDivide: operand = 4;
                    break;
            }

            // Now that we have numA and an operand, enable the calculate button
            Button myCalcButton = (Button) findViewById(R.id.btnCalculate);
            myCalcButton.setEnabled(true);
        }
        catch (Exception ex) {
            myEditText.setText("Err Parsing");
        }
    }



    // What to do when the calculate button is tapped
    public void clickCalculate(View v) {
        // As always, need a 'handle' on the EditText field
        EditText myEditText = (EditText)findViewById(R.id.txtDisplay);
        k7jtoMath myk7jtoMath = new k7jtoMath();
        try {
            numB = myEditText.getText().toString(); // Get the value for numB from EditText

            // Now choose which math to do, based on the operand we set when the user
            // tapped an operation button
            switch (operand) {
                case 1: result = myk7jtoMath.doAddition(numA, numB);
                    break;
                case 2: result = myk7jtoMath.doSubtraction(numA, numB);
                    break;
                case 3: result = myk7jtoMath.doMultiplication(numA, numB);
                    break;
                case 4: result = myk7jtoMath.doDivision(numA, numB);
                    break;
            }

            myEditText.setText(result); // Now that we have a result, display it

            // Now reset UI
            Button myCalcButton = (Button) findViewById(R.id.btnCalculate);
            myCalcButton.setEnabled(false);
            numA = "";
            numB = "";
            result = "";
        }
        catch(Exception ex) {
           myEditText.setText("Err Parsing");
        }
    }



    /* THIS IS THE ABOUT DIALOG */
    protected void showAbout(View v) {
        // Inflate the about message contents
        View messageView = getLayoutInflater().inflate(R.layout.about, null, false);

        // When linking text, force to always use default color. This works
        // around a pressed color state bug.
        TextView textView = (TextView) messageView.findViewById(R.id.about_credits);
        int defaultColor = textView.getTextColors().getDefaultColor();
        textView.setTextColor(defaultColor);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle(R.string.app_name);
        builder.setView(messageView);
        builder.create();
        builder.show();
    }

}
