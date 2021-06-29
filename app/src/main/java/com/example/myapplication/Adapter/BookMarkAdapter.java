package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.RestaurantsDetails;
import com.example.myapplication.R;

import java.util.List;

public class BookMarkAdapter extends RecyclerView.Adapter<BookMarkAdapter.MyViewHolder> {
    private Context applicationContext;
    private List<RestaurantsDetails> contacts;

    public BookMarkAdapter(Context applicationContext, List<RestaurantsDetails> contacts) {
        this.applicationContext = applicationContext;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(applicationContext).inflate(R.layout.zemato_frame,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvAddress.setText(contacts.get(position).getCuisines());
        holder.tvName.setText(contacts.get(position).getName());
        holder.tvCuisines.setText(contacts.get(position).getPrice());
        holder.tvPrice.setText(contacts.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        if (contacts !=null){
            return contacts.size();
        }else return 0;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPrice;
        TextView tvAddress;
        TextView tvName;
        TextView tvCuisines;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddress=itemView.findViewById(R.id.shop_address);
            tvName=itemView.findViewById(R.id.shop_name);
            tvPrice=itemView.findViewById(R.id.shop_price);
            tvCuisines=itemView.findViewById(R.id.shop_cuisines);
        }
    }
}
