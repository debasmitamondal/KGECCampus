package com.kgec.home.kgec_campus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    EditText editemail, editpasskey;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        editemail = (EditText) findViewById(R.id.editEmail);
        editpasskey = (EditText) findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonRegister).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRegister:
                startActivity(new Intent(this, RegisterActivity.class));

                break;
            case R.id.buttonLogin:
                user_login();
                break;

        }
    }

    private void user_login() {
        String email = editemail.getText().toString().trim();
        String passkey = editpasskey.getText().toString().trim();

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

        mAuth.signInWithEmailAndPassword(email, passkey).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            //Intent intent = new Intent(LoginActivity.this, mainactivity.class);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            // startActivity(intent);
                            Toast.makeText(getApplicationContext(),"Sign In Successful!",Toast.LENGTH_SHORT).show();
                        }


                    }
                }
        );
    }
}
