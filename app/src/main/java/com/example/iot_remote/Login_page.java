package com.example.iot_remote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_page extends AppCompatActivity {

    private Button b1,b2;
    private EditText user_name,email,pass;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        user_name=findViewById(R.id.temp_user);
        email=findViewById(R.id.login_email);
        pass=findViewById(R.id.login_password);

        mAuth = FirebaseAuth.getInstance();


        b1= findViewById(R.id.signup_button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Login_page.this, Signup_Activity.class);
                startActivity(intent);
            }
        });

        b2= findViewById(R.id.login_button);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int t=fun(email.getText().toString(),pass.getText().toString());


            }
        });
    }

    private int fun(String email, String password)
    {
        final int[] flag = {0};
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            user = mAuth.getCurrentUser();
                            Intent intent= new Intent(Login_page.this, Device_page.class);
                            intent.putExtra("name",user_name.getText().toString());
                            intent.putExtra("u_name",email);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Login_page.this, "Create a new Account",Toast.LENGTH_SHORT).show();
//                            Intent intent= new Intent(Login_page.this, Signup_Activity.class);
//                            startActivity(intent);
                        }
                    }
                });
        return flag[0];
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
    }
}