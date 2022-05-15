package com.example.etailor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.etailor.Databases.AccountDB;
import com.example.etailor.Databases.LoginDB;
import com.example.etailor.databinding.ActivitySelectionBinding;

public class Selection_Activity extends AppCompatActivity {
     ActivitySelectionBinding binding;
     String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent getEmail = getIntent();
        email = getEmail.getStringExtra("email");
        Global.email =email;
        //Toast.makeText(Selection_Activity.this, email, Toast.LENGTH_SHORT).show();


        LoginDB loginDB;
        loginDB =new LoginDB(this);
        String username;
        username = loginDB.getNameByEmail(email.trim());
        binding.SelectionTextview.setText(username);

        binding.myHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        binding.myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        binding.signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), Sign_in_Activity.class);
                Toast.makeText(Selection_Activity.this, "Sign out successful", Toast.LENGTH_SHORT).show();
                intent.putExtra("email",email);
                startActivity(intent);
                finish();
            }
        });

        AccountDB accountDB = new AccountDB(this);
        Boolean checkemail = accountDB.checkemail(email);
        binding.myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AccountActivity.class);
                intent.putExtra("email",email);
                if(checkemail==true){
                    intent.putExtra("regtype",1);
                }
                startActivity(intent);
                finish();
            }
        });

        binding.myTailor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), TailorActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
                finish();
            }
        });
        binding.myInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AboutActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
                finish();
            }
        });

    }
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), Selection_Activity.class);
        intent.putExtra("email",email);
        startActivity(intent);
        finish();
    }
}