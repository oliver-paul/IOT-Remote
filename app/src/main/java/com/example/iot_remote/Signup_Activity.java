package com.example.iot_remote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_Activity extends AppCompatActivity {

    private Button signup;
    private EditText name,email,password,cpassword;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_);



        name=findViewById(R.id.fullname);
        email=findViewById(R.id.emailid);
        password=findViewById(R.id.passwordid);
        cpassword=findViewById(R.id.confirm_password);
        signup=findViewById(R.id.signup_page_btn);

        database= FirebaseDatabase.getInstance();
        myRef= database.getReference();
        mAuth = FirebaseAuth.getInstance();

       // password.getText().toString().equals(cpassword.getText().toString())?fun():Toast.makeText(this,"Hello",Toast.LENGTH_LONG);

    }

    public void fun(View view)
    {
        if(password.getText().toString().equals(cpassword.getText().toString())) {
            if(name.getText().toString().trim().equals("")||email.getText().toString().trim().equals(""))
            {
                Toast.makeText(this,"Fill up all the Fields",Toast.LENGTH_LONG).show();
                //email.setError("Fill up all the Fields");
            }
            else
            {
                if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
                {
                    Toast.makeText(this,"Enter a valid email id ",Toast.LENGTH_LONG).show();
                }
                else
                 {
                     String e=email.getText().toString();
                     String p=password.getText().toString();
                     String n=name.getText().toString();
                    if(add_account(e,p))
                    {

                        Getter_setter g= new Getter_setter(n,e);
                        myRef.child(name.getText().toString()).child("User_Information").setValue(g);
                        Intent intent = new Intent(Signup_Activity.this, Login_page.class);

                        finish();
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(this,"Cant Change",Toast.LENGTH_LONG).show();
                 }
            }
        }
        else
        {
            Toast.makeText(this,"Password not Matching",Toast.LENGTH_LONG).show();
        }
    }

    private boolean add_account(String email,String password)
    {
        final int[] flag = {0};
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            user = mAuth.getCurrentUser();
                            Toast.makeText(Signup_Activity.this, "Authentication is Done",Toast.LENGTH_SHORT).show();

                            flag[0] =1;
                        }
                        else
                            {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Signup_Activity.this, "User already Exits",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        if (flag[0]==1)
            return false;
        else
            return true;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }




}