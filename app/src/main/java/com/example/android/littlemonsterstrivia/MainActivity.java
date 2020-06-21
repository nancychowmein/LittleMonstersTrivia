package com.example.android.littlemonsterstrivia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.littlemonsterstrivia.R;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** This determines the checked state of the checkbox. */
    public void onCheckboxClicked (View view) {
        boolean checked = ((CheckBox) view).isChecked();
    }

    /**
     * This determines the selected state of the radio button.
     */
    public void onRadioButtonSelected(View view) {
        // Check if radio button is clicked
        boolean selected = ((RadioButton)view).isChecked();
    }

    /**
     * This method is called when the submit button is clicked.
     */
    public void submitQuiz(View view) {
        // Figure out if user checked Grammy Award
        CheckBox grammyCheckBox = (CheckBox) findViewById(R.id.grammy_checkbox);
        boolean hasGrammy = grammyCheckBox.isChecked();

        // Figure out if user checked Academy Award
        CheckBox academyCheckBox = (CheckBox) findViewById(R.id.academy_checkbox);
        boolean hasAcademy = academyCheckBox.isChecked();

        // Figure out if user checked BAFTA Award
        CheckBox baftaCheckBox = (CheckBox) findViewById(R.id.bafta_checkbox);
        boolean hasBafta = baftaCheckBox.isChecked();

        // Figure out if user checked Emmy Award
        CheckBox emmyCheckBox = (CheckBox) findViewById(R.id.emmy_checkbox);
        boolean hasEmmy = emmyCheckBox.isChecked();

        // Figure out if user selected Blackpink
        RadioButton blackpinkRadioButton = (RadioButton) findViewById(R.id.blackpink_radiobutton);
        boolean selectBlackpink = blackpinkRadioButton.isChecked();

        // Figure out if user selected f(x)
        RadioButton fxRadioButton = (RadioButton) findViewById(R.id.fx_radiobutton);
        boolean selectFx = fxRadioButton.isChecked();

        // Figure out if user selected BTS
        RadioButton btsRadioButton = (RadioButton) findViewById(R.id.bts_radiobutton);
        boolean selectBts = btsRadioButton.isChecked();

        // Figure out if user selected Super Bowl 50
        RadioButton sb50RadioButton = (RadioButton) findViewById(R.id.sb50_radiobutton);
        boolean select50 = sb50RadioButton.isChecked();

        // Figure out if user selected Super Bowl LI
        RadioButton sbLIRadioButton = (RadioButton) findViewById(R.id.sbLI_radiobutton);
        boolean selectLI = sbLIRadioButton.isChecked();

        // Figure out if user selected Super Bowl LII
        RadioButton sbLIIRadioButton = (RadioButton) findViewById(R.id.sbLII_radiobutton);
        boolean selectLII = sbLIIRadioButton.isChecked();

        // Figure out if user selected 2011
        RadioButton year2011RadioButton = (RadioButton) findViewById(R.id.year2011_radiobutton);
        boolean select2011 = year2011RadioButton.isChecked();

        // Figure out if user selected 2012
        RadioButton year2012RadioButton = (RadioButton) findViewById(R.id.year2012_radiobutton);
        boolean select2012 = year2012RadioButton.isChecked();

        // Figure out if user selected 2014
        RadioButton year2014RadioButton = (RadioButton) findViewById(R.id.year2014_radiobutton);
        boolean select2014 = year2014RadioButton.isChecked();

        // Show error toast if no K-pop group is selected
        if (selectBlackpink == false && selectFx == false && selectBts == false) {
            // Show an error message as a toast
            Toast.makeText(this, "Please select a K-pop group.", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        // Show error toast if no Super Bowl is selected
        if (select50 == false && selectLI == false && selectLII == false) {
            // Show an error message as a toast
            Toast.makeText(this, "Please select a Super Bowl.", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        // Show error toast if no year is selected
        if (select2011 == false && select2012 == false && select2014 == false) {
            // Show an error message as a toast
            Toast.makeText(this, "Please select a year.", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        // Get the name the user has input
        EditText nameField = findViewById(R.id.name);
        String name = nameField.getText().toString();

        // Show error toast if no name is provided
        if (TextUtils.isEmpty(name)) {
            // Show an error message as a toast
            Toast.makeText(this, "Please enter a name, Little Monster.", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        // Create order summary based on user inputs
        String message = createQuizSummary(hasAcademy, hasBafta, hasEmmy, hasGrammy, selectBlackpink, selectFx, selectBts, select50, selectLI, selectLII, select2011, select2012, select2014, name);

        //TextView finalOrder = (TextView) findViewById(R.id.create_order_summary);
        //finalOrder.setText(message);

        // Set up the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        builder.setTitle("Trivia Results for " + (name));
        // Display confirmation of the order summary message
        builder.setMessage(message);
        // Add one button
        builder.setPositiveButton("OK", null);
        // Create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    /**
     * This creates an order summary.
     * @param hasAcademy if user selected Academy Award
     * @param hasBafta if user selected BAFTA Award
     * @param hasEmmy if user selected Emmy Award
     * @param hasGrammy if user selected Grammy Award
     * @param selectBlackpink if user picked Blackpink
     * @param selectFx if user picked f(x)
     * @param selectBts if user picked BTS
     * @param name displays name of user
     * @param select50 if user chose Super Bowl 50
     * @param selectLI if user chose Super Bowl LI
     * @param selectLII if user chose Super Bowl LII
     * @param select2011 if user selected year 2011
     * @param select2012 if user selected year 2012
     * @param select2014 if user selected year 2014
     * @return text summary of the quiz
     */
    private String createQuizSummary(boolean hasAcademy, boolean hasBafta, boolean hasEmmy, boolean hasGrammy, boolean selectBlackpink, boolean selectFx, boolean selectBts, boolean select50, boolean selectLI, boolean selectLII, boolean select2011, boolean select2012, boolean select2014, String name) {
        String message = "Thanks for your support, Little Monster!\n";

        if (selectBlackpink == true && selectBts == false && selectFx == false) {
            message += "\nQuestion 1: Correct! Her song with Blackpink is called Sour Candy.";
        } else if (selectBlackpink == false && selectBts == true && selectFx == false) {
            message += "\nQuestion 1: Incorrect. Try again.";
        } else if (selectBlackpink == false && selectBts == false && selectFx == true) {
            message += "\nQuestion 1: Incorrect. Try again.";
        }

        message += "\n";

        if (select50 == true && selectLI == false && selectLII == false) {
            message += "\nQuestion 2: Incorrect. Try again.";
        } else if (select50 == false && selectLI == true && selectLII== false) {
            message += "\nQuestion 2: Correct! Lady Gaga headlined the halftime show for Super Bowl LI in 2017 in Houston, Texas.";
        } else if (select50 == false && selectLI == false && selectLII == true) {
            message += "\nQuestion 2: Incorrect. Try again.";
        }

        message += "\n";

        if (select2011 == true && select2012 == false && select2014 == false) {
            message += "\nQuestion 3: Incorrect. Try again.";
        } else if (select2011 == false && select2012 == true && select2014== false) {
            message += "\nQuestion 3: Correct! Lady Gaga created the foundation with her mother to support young people's mental health and wellness.";
        } else if (select2011 == false && select2012 == false && select2014 == true) {
            message += "\nQuestion 3: Incorrect. Try again.";
        }

        message += "\n";

        if (hasAcademy == true && hasBafta == true && hasGrammy == true && hasEmmy == false) {
            message += "\nQuestion 4: Correct! Lady Gaga has been recognized for her multi-dimensional talents with Academy, BAFTA, and Grammy awards.";
        } else if (hasEmmy == true) {
            message += "\nQuestion 4: Incorrect. Try again.";
        } else if (hasAcademy == false) {
            message += "\nQuestion 4: Incorrect. Try again.";
        } else if (hasBafta == false) {
            message += "\nQuestion 4: Incorrect. Try again.";
        } else if (hasGrammy == false) {
            message += "\nQuestion 4: Incorrect. Try again.";
        }

        return message;
    }

}
