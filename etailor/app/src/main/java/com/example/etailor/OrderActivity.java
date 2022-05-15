package com.example.etailor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.etailor.Adapters.OrdersAdapter;
import com.example.etailor.Databases.OrdersDB;
import com.example.etailor.Models.OrdersModel;
import com.example.etailor.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;
    String email;
    private int OrderFromWhere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent getEmail = getIntent();
        email = getEmail.getStringExtra("email");
        Global.email =email;
        //Toast.makeText(OrderActivity.this, email, Toast.LENGTH_SHORT).show();

        OrderFromWhere = getEmail.getIntExtra("OrderFromWhere",0);

        OrdersDB helper = new OrdersDB(this);
        ArrayList<OrdersModel>list =helper.getOrders();

        OrdersAdapter adapter = new OrdersAdapter(list, this);
        binding.ordersRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.ordersRecyclerView.setLayoutManager(layoutManager);
    }
    public void onBackPressed() {
        Intent intent = new Intent();
        if(OrderFromWhere==0){
        intent.setClass(getApplicationContext(), Selection_Activity.class);}
        if(OrderFromWhere==2){
            intent.setClass(getApplicationContext(), Sign_in_Activity.class);
        }
        else{
            intent.setClass(getApplicationContext(), HomeActivity.class);
        }
        Global.email =email;
        intent.putExtra("email",email);
        startActivity(intent);
        finish();
    }
}