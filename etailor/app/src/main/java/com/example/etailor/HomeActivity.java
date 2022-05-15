package com.example.etailor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.etailor.databinding.ActivityHomeBinding;
import com.example.etailor.Adapters.MainAdapter;
import com.example.etailor.Models.MainModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<MainModel> list = new ArrayList<>();

        Intent getEmail = getIntent();
        email = getEmail.getStringExtra("email");
        Global.email =email;
        //Toast.makeText(HomeActivity.this, email, Toast.LENGTH_SHORT).show();

        list.add(new MainModel(R.drawable.short_kurta,"Short Kurta","1200","Great comfort wear for day to day use and made with cotton. Design to custom fit you ",email));
        list.add(new MainModel(R.drawable.suit,"Suit","9000","Formal suit with simple but great style . Recommend for professional use.",email));
        list.add(new MainModel(R.drawable.bandhgala_suit,"Bandhgala Suit","8000","A Jodhpuri suit or Bandhgala suit, is a formal suit from India. It originated in the Jodhpur State, and was popularized during 20th century.",email));
        list.add(new MainModel(R.drawable.bandi,"Bandi","3000","Bagalbandi is an ethnic costume of the Indian subcontinent; the garment is more associated with Hindi belt, Gujarat, Maharastra, Nepal.",email));
        list.add(new MainModel(R.drawable.sherwani,"Sherwani","15000","It originated in the 19th century British India as the European style court dress of regional Mughal nobles and royals of northern India",email));
        list.add(new MainModel(R.drawable.jacket,"Jacket","6000","High quality Jacket with custom fit to your choice",email));
        list.add(new MainModel(R.drawable.waistcoat,"Waistcoat","5000","Machine Wash Separately In Cold Water. Wash Inside Out With Mild Detergent. Hang In Shade To Dry. ",email));
        list.add(new MainModel(R.drawable.trouser,"Trouser","1500","an outer garment covering the lower half of the body from the waist to the ankles and divided into sections to cover each leg separately.",email));



        MainAdapter adapter = new MainAdapter(list,this);
        binding.recylerview.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recylerview.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.orders:
                Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("OrderFromWhere",1);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), Selection_Activity.class);
        intent.putExtra("email",email);
        startActivity(intent);
        finish();
    }
}