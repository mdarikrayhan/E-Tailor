package com.example.etailor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.etailor.Databases.LoginDB;
import com.example.etailor.databinding.ActivitySignInBinding;
import android.app.Application;

public class Sign_in_Activity extends AppCompatActivity {
    ActivitySignInBinding binding;
    LoginDB DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DB = new LoginDB(this);
        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.emailSignIn.getText().toString();
                String pass = binding.passwordSignIn.getText().toString();

                Global.email=email;
                if(email.equals(Global.admin)||pass.equals(Global.adminpass)){
                    Toast.makeText(Sign_in_Activity.this, "Welcome Master Tailor", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
                    intent.putExtra("email",email);
                    intent.putExtra("OrderFromWhere",2);
                    startActivity(intent);
                }
                else if(email.equals("")||pass.equals(""))
                    Toast.makeText(Sign_in_Activity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(email, pass);
                    if(checkuserpass==true){
                        Toast.makeText(Sign_in_Activity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(),Selection_Activity.class);
                        intent.putExtra("email",email);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Sign_in_Activity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
       binding.btnSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),Sign_up_Activity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),Sign_in_Activity.class);
        startActivity(intent);
        finish();
    }
}