package com.example.home.test_firebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText em;
    private EditText pass;
    private Button btn1;
    private TextView text;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth= FirebaseAuth.getInstance(); //initialises firebase object

        progressDialog= new ProgressDialog(this);
        em = (EditText) findViewById(R.id.editEmail);
        pass = (EditText) findViewById(R.id.editPassword);
        btn1 = (Button) findViewById(R.id.logintext);
        text = (TextView) findViewById(R.id.textRegister);

        btn1.setOnClickListener(this);
        text.setOnClickListener(this);
    }

    private void register()
    {
        String email = em.getText().toString().trim();
        String password= pass.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"Email is not entered",Toast.LENGTH_SHORT).show();
            //stopping the function
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            //password is empty
            Toast.makeText(this, "Password is not entered", Toast.LENGTH_SHORT).show();
        }
        //if validations are ok
        //show a progress bar
        progressDialog.setMessage("Registering user..");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"Registerd Successfully",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Not Registerd Successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == btn1) {
            register();

        }
        if (view == text) {
            //login activity
        }
    }
}
