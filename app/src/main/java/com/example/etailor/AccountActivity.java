package com.example.etailor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.etailor.Databases.AccountDB;
import com.example.etailor.databinding.ActivityAccountBinding;

public class AccountActivity extends AppCompatActivity {
    ActivityAccountBinding binding;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent getEmail = getIntent();
        email = getEmail.getStringExtra("email");
        Global.email =email;
        //Toast.makeText(AccountActivity.this, email, Toast.LENGTH_SHORT).show();
        AccountDB accountDB = new AccountDB(this);


        if(getIntent().getIntExtra("regtype",0)==0){
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = accountDB.insertAccount(
                        email,
                        binding.collar.getText().toString(),
                        binding.chest.getText().toString(),
                        binding.sleeve.getText().toString(),
                        binding.waist.getText().toString(),
                        binding.insideleg.getText().toString(),
                        binding.outsideleg.getText().toString(),
                        binding.centreebacklenght.getText().toString()
                );
                if(isInserted)
                    Toast.makeText(AccountActivity.this, "Data Success.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AccountActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        }
        else{
            Cursor cursor = accountDB.getAccountByEmail(email);

            binding.collar.setText(cursor.getString(1));
            binding.chest.setText(cursor.getString(2));
            binding.sleeve.setText(cursor.getString(3));
            binding.waist.setText(cursor.getString(4));
            binding.insideleg.setText(cursor.getString(5));
            binding.outsideleg.setText(cursor.getString(6));
            binding.centreebacklenght.setText(cursor.getString(7));

            binding.btnSubmit.setText("Update");
            binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isUpdated = accountDB.updateAccount(
                            binding.collar.getText().toString(),
                            binding.chest.getText().toString(),
                            binding.sleeve.getText().toString(),
                            binding.waist.getText().toString(),
                            binding.insideleg.getText().toString(),
                            binding.outsideleg.getText().toString(),
                            binding.centreebacklenght.getText().toString(),
                            email
                    );
                    if(isUpdated)
                        Toast.makeText(AccountActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(AccountActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), Selection_Activity.class);
        intent.putExtra("email",email);
        startActivity(intent);
        finish();
    }
}