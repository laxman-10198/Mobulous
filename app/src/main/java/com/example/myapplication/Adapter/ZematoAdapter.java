package com.example.myapplication.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interface.ZematoListener;
import com.example.myapplication.LocalDataBase.SQLiteDataBase;
import com.example.myapplication.Model.RestaurantsDetails;
import com.example.myapplication.Model.Zemato.Restaurants;
import com.example.myapplication.R;

import java.util.List;

public class ZematoAdapter extends RecyclerView.Adapter<ZematoAdapter.MyViewHolder> {
    private Context applicationContext;
    private List<Restaurants> restaurants;
    ZematoListener zematoListener;
    SQLiteDataBase db;
    public ZematoAdapter(Context applicationContext, List<Restaurants> restaurants,ZematoListener zematoListener) {
        this.applicationContext = applicationContext;
        this.restaurants = restaurants;
        this.zematoListener=zematoListener;
        db = new SQLiteDataBase(applicationContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(applicationContext).inflate(R.layout.zemato_frame,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {
            if (restaurants !=null && !restaurants.isEmpty()){
                holder.tvCuisines.setText(restaurants.get(position).getRestaurant().getCuisines());
                holder.tvName.setText(restaurants.get(position).getRestaurant().getName());
                holder.tvPrice.setText(String.valueOf(restaurants.get(position).getRestaurant().getAverageCostForTwo()));
                holder.tvAddress.setText(restaurants.get(position).getRestaurant().getLocation().getAddress());
            }else zematoListener.zematoSearchDataStatus("Not Data Found");
        }catch (Exception e){
        }

    }

    @Override
    public int getItemCount() {
        if (restaurants !=null && !restaurants.isEmpty())
        return restaurants.size();
        else return 0;
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name="",price="",cuisines="",address="";
                    name=restaurants.get(getAdapterPosition()).getRestaurant().getName();
                    price=String.valueOf(restaurants.get(getAdapterPosition()).getRestaurant().getAverageCostForTwo());
                    cuisines=restaurants.get(getAdapterPosition()).getRestaurant().getCuisines();
                    address=restaurants.get(getAdapterPosition()).getRestaurant().getLocation().getAddress();
                    db.addContact(new RestaurantsDetails(name,price,address,cuisines));
                    Toast.makeText(applicationContext,"Added Item",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
