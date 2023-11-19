package com.example.carbooking;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerOption;
    private TextInputEditText etPay, etDob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Spinner and TextInputEditText
        spinnerOption = findViewById(R.id.spinnerOption);
        etPay = findViewById(R.id.etPay);
        etDob = findViewById(R.id.etDob);

        // Create ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.booking_options, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerOption.setAdapter(adapter);

        // Set up OnItemSelectedListener for the spinner
        spinnerOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle the selection based on position
                calculatePay(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected
            }
        });

        // Set up onClickListener for the "Book" button
        Button buttonNext01 = findViewById(R.id.buttonNext01);
        buttonNext01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the booking and display a toast
                performBooking();
            }
        });
    }

    private void calculatePay(int position) {
        // Update payment based on selected option
        switch (position) {
            case 0: // Daily
                etPay.setText("300");
                break;
            case 1: // Weekly
                etPay.setText("1750");
                break;
            case 2: // Monthly
                etPay.setText("6000");
                break;
        }
    }

    private void performBooking() {
        // Add your booking logic here
        Toast.makeText(this, "You have successfully paid, and we attend to you as soon as possible.", Toast.LENGTH_SHORT).show();
    }

    // Method for handling date picker dialog
    public void showDatePickerDialog(View v) {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        // Update the TextInputEditText with the selected date
                        etDob.setText(selectedDayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear);
                    }
                },
                year, month, day);

        // Show the date picker dialog
        datePickerDialog.show();
    }
}

