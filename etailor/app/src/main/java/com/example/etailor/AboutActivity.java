package com.example.etailor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.etailor.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {
    ActivityAboutBinding binding;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent getEmail = getIntent();
        email = getEmail.getStringExtra("email");
        Global.email =email;
        //Toast.makeText(AboutActivity.this, email, Toast.LENGTH_SHORT).show();
    }
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), Selection_Activity.class);
        intent.putExtra("email",email);
        startActivity(intent);
        finish();
    }
}