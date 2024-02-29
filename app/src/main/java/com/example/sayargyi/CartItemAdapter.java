package com.example.sayargyi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {

    private List<FoodItem> cartItemList;
    private Context context;

    public CartItemAdapter(Context context, List<FoodItem> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cart_item, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        FoodItem item = cartItemList.get(position);
        holder.textViewCartItemName.setText(item.getName());
        holder.textViewCartItemPrice.setText(String.format("Price: $%.2f", item.getPrice()));
        holder.textViewCartItemQuantity.setText("Qty: " + item.getQuantity());
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class CartItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCartItemName, textViewCartItemPrice, textViewCartItemQuantity;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCartItemName = itemView.findViewById(R.id.textViewCartItemName);
            textViewCartItemPrice = itemView.findViewById(R.id.textViewCartItemPrice);
            textViewCartItemQuantity = itemView.findViewById(R.id.textViewCartItemQuantity);
        }
    }
}
