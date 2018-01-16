package com.example.home.register;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydata;
    EditText name, roll, email, phone, dept, sem, paswrd;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydata = new DatabaseHelper(this);

        name = (EditText) findViewById(R.id.editName);
        roll = (EditText) findViewById(R.id.editRollNumber);
        email = (EditText) findViewById(R.id.editEmail);
        phone = (EditText) findViewById(R.id.editPhone);
        dept = (EditText) findViewById(R.id.editDepartment);
        sem = (EditText) findViewById(R.id.editSemester);
        paswrd = (EditText) findViewById(R.id.editPassword);
        btn = (Button) findViewById(R.id.button);

        AddData();

    }


    public void AddData() {
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydata.insertData(name.getText().toString(),
                                roll.getText().toString(),
                                email.getText().toString(), phone.getText().toString(),
                                dept.getText().toString(), sem.getText().toString(), paswrd.getText().toString() );
                        if (isInserted == true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}




