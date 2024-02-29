package com.example.sayargyi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartItemAdapter cartItemAdapter;
    private DatabaseHelper1 databaseHelper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_layout);

        recyclerView = findViewById(R.id.recyclerViewCartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseHelper1 = new DatabaseHelper1(this);

        // Retrieve cart items from the database
        List<FoodItem> cartItems = databaseHelper1.getAllItemsInCart();

        // Set up the RecyclerView adapter
        cartItemAdapter = new CartItemAdapter(this, cartItems);
        recyclerView.setAdapter(cartItemAdapter);
    }
}
