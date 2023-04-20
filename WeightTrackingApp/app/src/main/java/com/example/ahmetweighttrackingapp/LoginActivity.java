package com.example.ahmetweighttrackingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login, createAccount, notif;
    SQLiteDB database;
    private int PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);

        username = (EditText) findViewById(R.id.editTextName);
        password = (EditText) findViewById(R.id.editTextPassword);

        login = (Button) findViewById(R.id.loginBtn);
        createAccount = (Button) findViewById(R.id.accountBtn);
        notif = (Button) findViewById(R.id.notifBtn);

        database = new SQLiteDB(this);

        // Listener for login button, conditionals for input errors and success toast messages
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Fields empty, try again", Toast.LENGTH_SHORT).show();
                else {
                    Boolean passCheck = database.passwordCheck(user, pass);
                    if (passCheck) {
                        Toast.makeText(LoginActivity.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), WeightsActivity.class);
                        startActivity(intent);  // This will start the main list view activity showing all daily weights
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Username or Password Invalid", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Listener for login button, conditionals for input errors and success toast messages
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Fields empty, try again", Toast.LENGTH_SHORT).show();
                else {
                    Boolean userExists = database.userCheck(user);
                    if (!userExists) {
                        Boolean insert = database.insertData(user, pass);
                        if (insert) {
                            Toast.makeText(LoginActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),WeightsActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "User Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"User Already Exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Listener for enabling notifications for SMS
        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(LoginActivity.this,
                        android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(LoginActivity.this, "Permission Already Granted", Toast.LENGTH_SHORT).show();
                }
                else{
                    requestSMSPermission();
                }
            }
        });
    }

    // Sets permissions according to what user chose when pressing the "enable notifications button"
    private void requestSMSPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.SEND_SMS)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Required")
                    .setMessage("Permission needed to send messages")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(LoginActivity.this, new String[] {android.Manifest.permission.SEND_SMS}, PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        }
        else{
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.SEND_SMS}, PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}