package com.example.etailor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.etailor.Databases.LoginDB;
import com.example.etailor.databinding.ActivitySignUpBinding;

public class Sign_up_Activity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    LoginDB DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DB = new LoginDB(this);

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.emailSignUp.getText().toString();
                String pass = binding.passwordSignUp.getText().toString();
                String repass = binding.confPasswordSignUp.getText().toString();
                String username = binding.nameSignUp.getText().toString();
                if(email.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(Sign_up_Activity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkemail(email);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(email, pass,username);
                            if(insert==true){
                                Toast.makeText(Sign_up_Activity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Sign_in_Activity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Sign_up_Activity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Sign_up_Activity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Sign_up_Activity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });
        binding.btnSignInPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Sign_in_Activity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.setClass(getApplicationContext(), Sign_in_Activity.class);
        startActivity(i);
        finish();
    }
}