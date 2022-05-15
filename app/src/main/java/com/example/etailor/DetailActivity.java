package com.example.etailor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.etailor.Databases.OrdersDB;
import com.example.etailor.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    int count = 1;
    String email;
    Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        OrdersDB helper = new OrdersDB(this);

        if(getIntent().getIntExtra("type",0)==1){

            final int image = getIntent().getIntExtra("image",0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");
            email = getIntent().getStringExtra("email");

            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d",price));
            binding.nameLbl.setText(name);
            binding.detailDescription.setText(description);



            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count++;
                    binding.quantity.setText(""+count);
                }
            });

            binding.subtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(count<=1)
                        count=1;
                    else
                        count--;
                    binding.quantity.setText(""+count);
                }
            });

            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isInserted = helper.insertOrder(
                            binding.addressBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            description,
                            name,
                            Integer.parseInt(binding.quantity.getText().toString()),
                            email
                    );
                    if(isInserted)
                        Toast.makeText(DetailActivity.this, "Data Success.", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            int id = getIntent().getIntExtra("id",0);
            Cursor cursor = helper.getOrderById(id);
            final int image = cursor.getInt(4);

            binding.detailImage.setImageResource(cursor.getInt(4));
            binding.priceLbl.setText(String.format("%d",cursor.getInt(3)));
            binding.nameLbl.setText(cursor.getString(6));
            binding.detailDescription.setText(cursor.getString(5));
            binding.quantity.setText(cursor.getString(7));
            binding.addressBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertBtn.setText("Update Now");

            count = Integer.parseInt(binding.quantity.getText().toString());
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count++;
                    binding.quantity.setText(""+count);
                }
            });

            binding.subtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(count<=1)
                        count=1;
                    else
                        count--;
                    binding.quantity.setText(""+count);
                }
            });

            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isUpdated =  helper.updateOrder(
                            binding.addressBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceLbl.getText().toString()),
                            image,
                            binding.detailDescription.getText().toString(),
                            binding.nameLbl.getText().toString(),
                            Integer.parseInt(binding.quantity.getText().toString()),
                            id);
                    if(isUpdated)
                        Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), HomeActivity.class);
        intent.putExtra("email",email);
        startActivity(intent);
        finish();
    }
}