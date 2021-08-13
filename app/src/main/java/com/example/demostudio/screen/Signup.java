package com.example.demostudio.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.demostudio.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Signup extends AppCompatActivity {
    Calendar myCalendar;
    EditText birthday, name, email, pwd, rePwd, pno;
    RadioButton male, female;
    RadioGroup gender;
    CheckBox termsNcondition;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();

        setContentView(R.layout.activity_signup);

        myCalendar = Calendar.getInstance();
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        pno = (EditText) findViewById(R.id.phno);
        birthday = (EditText) findViewById(R.id.birthday);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        termsNcondition = findViewById(R.id.termsNcondition);
        gender = findViewById(R.id.radioGrp);
        signup = findViewById(R.id.signupBtn);
        pwd = (EditText) findViewById(R.id.pwd);
        rePwd = (EditText) findViewById(R.id.rePwd);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAllFields();
            }
        });
        birthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Signup.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        birthday.setText(sdf.format(myCalendar.getTime()));
    }

    private boolean CheckAllFields() {
        if (name.length() == 0) {
            name.setError("This field is required");
            return false;
        }
        if (email.length() == 0) {
            email.setError("This field is required");
            return false;
        }
        if (email.length() < 1) {
            emailValidator();
            return false;
        }
        if (birthday.length() == 0) {
            birthday.setError("This field is required");
            return false;
        }
        if (pno.length() < 10 || pno.length() > 12) {
            pno.setError("Phone number must be greater than 9");
            return false;
        }
        if (gender.getCheckedRadioButtonId() == -1) {
            int lastChildPos = gender.getChildCount() - 1;
            ((RadioButton) gender.getChildAt(lastChildPos)).setError("This field is required");
            return false;
        }
        if (pwd.length() < 8) {
            pwd.setError("Password must be minimum 8 characters");
            return false;
        }
        if (rePwd.length() < 8) {
            rePwd.setError("Password must be minimum 8 characters");
            return false;
        } else if (termsNcondition.isChecked() == false) {
            termsNcondition.setError("This field is required");
            return false;
        }
        return true;
    }

    public void emailValidator() {
        String emailToText = email.getText().toString();
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
        }
    }
}