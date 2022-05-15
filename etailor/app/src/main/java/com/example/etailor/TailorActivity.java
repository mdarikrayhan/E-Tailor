package com.example.etailor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.etailor.databinding.ActivityTailorBinding;

public class TailorActivity extends AppCompatActivity {
    ActivityTailorBinding binding;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTailorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent getEmail = getIntent();
        email = getEmail.getStringExtra("email");
        Global.email =email;
        //Toast.makeText(TailorActivity.this, email, Toast.LENGTH_SHORT).show();
    }
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), Selection_Activity.class);
        intent.putExtra("email",email);
        startActivity(intent);
        finish();
    }
}