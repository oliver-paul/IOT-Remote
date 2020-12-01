package com.example.iot_remote;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Device_page extends AppCompatActivity implements ExampleDialog.ExampleDialogListener{

    private ListView device_list;
    private TextView nick;
    private ImageView add,profile;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference mref;
    FirebaseAuth mAuth;

    public Activity c;
    public Dialog d;

    String Temp,main_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_page);

        device_list=findViewById(R.id.list_view);
        nick=findViewById(R.id.device_nick);
        add=findViewById(R.id.add_device);
        profile=findViewById(R.id.profile_pic);

        String t=getIntent().getStringExtra("name");
        nick.setText(t);
        Temp=getIntent().getStringExtra("u_name");
        Toast.makeText(this,Temp,Toast.LENGTH_LONG).show();

        firebaseDatabase=FirebaseDatabase.getInstance();
        mref=firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                fun();            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


    }

    private void openDialog()
    {
        ExampleDialog e= new ExampleDialog();
        e.show(getSupportFragmentManager(),"example dialog");
    }


    private void fun()
    {
        Toast.makeText(this,"Adding Profile Pic Feature will be added in the next update",Toast.LENGTH_LONG).show();
    }


    @Override
    public void applyTexts(String username, String password)
    {
        Toast.makeText(this,username+"  "+password,Toast.LENGTH_LONG).show();
        //add_firebase(username,password);
    }

    private void add_firebase(String username, String password)
    {

    }




    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if(mAuth.getCurrentUser()== null)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

    }
}