package com.example.etailor.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.etailor.Databases.OrdersDB;
import com.example.etailor.DetailActivity;
import com.example.etailor.Models.OrdersModel;
import com.example.etailor.R;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.viewHolder>{
    ArrayList<OrdersModel> list;
    Context context;

    public OrdersAdapter(ArrayList<OrdersModel>list ,Context context){
        this.list =list;
        this.context=context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final OrdersModel model = list.get(position);
        holder.orderImage.setImageResource(model.getOrderImage());
        holder.soldItemName.setText(model.getSoldItemName());
        holder.orderNumber.setText(model.getOrderNumber());
        holder.price.setText(model.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("id",Integer.parseInt(model.getOrderNumber()));
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setMessage("Are you sure to delete this item ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                OrdersDB helper = new OrdersDB(context);
                                if( helper.deleteOrder(model.getOrderNumber())>0){
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView orderImage;
        TextView soldItemName,orderNumber,price;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            orderImage =itemView.findViewById(R.id.orderImage);
            soldItemName =itemView.findViewById(R.id.orderItemName);
            orderNumber =itemView.findViewById(R.id.orderNumber);
            price =itemView.findViewById(R.id.soldOrderPrice);
        }
    }
}
