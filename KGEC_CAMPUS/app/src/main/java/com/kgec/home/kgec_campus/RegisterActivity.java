package com.kgec.home.kgec_campus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    Spinner spinner;
    EditText editname, editregistration, editemail, editpasskey, editdepartment;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.semester, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        editname = (EditText) findViewById(R.id.editTextName);
        editregistration = (EditText) findViewById(R.id.editTextregistration);
        editemail = (EditText) findViewById(R.id.editTextemail);
        editpasskey = (EditText) findViewById(R.id.editPassword);
        editdepartment = (EditText) findViewById(R.id.editTextdept);

        findViewById(R.id.buttonRegister).setOnClickListener(this);
        findViewById(R.id.textViewlogin).setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.ProgressBar);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRegister:
                register_user();
                break;

            case R.id.textViewlogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    private void register_user() {
        String name = editname.getText().toString().trim();
        String registration = editregistration.getText().toString().trim();
        String email = editemail.getText().toString().trim();
        String passkey = editpasskey.getText().toString().trim();
        String department = editdepartment.getText().toString().trim();

        if (department.isEmpty()) {
            editdepartment.setError("department is required ");
            editdepartment.requestFocus();
            return;
        }
        if (name.isEmpty()) {
            editname.setError("Name is required ");
            editname.requestFocus();
            return;
        }
        if (registration.isEmpty()) {
            editregistration.setError("Registration number is required ");
            editregistration.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editemail.setError("Email is required ");
            editemail.requestFocus();                  //to show the error
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemail.setError(" Valid Email is required ");
            editemail.requestFocus();
            return;
        }
        if (passkey.isEmpty()) {
            editpasskey.setError("password is required ");
            editpasskey.requestFocus();
            return;
        }
        if (passkey.length() < 6) {
            editpasskey.setError("password is too small ");
            editpasskey.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, passkey)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            //Intent intent = new Intent(RegisterActivity.this, mainactivity.class);
                           // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                           // startActivity(intent);
                            Toast.makeText(getApplicationContext(), "You are  registered sucessfully  .",
                                    Toast.LENGTH_SHORT).show();
                        } else if (task.getException() instanceof FirebaseAuthUserCollisionException)     //to check if email is already registered.
                        {
                            Toast.makeText(getApplicationContext(), "You are  already Registered  .",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();                                        // to check the exception .

                        }
                    }


                });
    }
}
