package com.example.sayargyi;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<FoodItem> foodItemList = new ArrayList<>();
    private DatabaseHelper1 dbHelper;
    private ImageView sort;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DatabaseHelper1(this);
        sort = findViewById(R.id.sort);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new FoodItemAdapter(foodItemList); // Update to remove listener
        recyclerView.setAdapter(adapter);

       // FoodItem burger = new FoodItem("Burger", "HHHH", 10.0);
        //dbHelper.addFoodItem(burger);


        // Other code
        prepareFoodItems();
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the cart activity
                Intent intent = new Intent(home.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void prepareFoodItems() {
        // Clear existing data
        foodItemList.clear();

        // Fetch data from database
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper1.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(DatabaseHelper1.COLUMN_ID);
                int nameIndex = cursor.getColumnIndex(DatabaseHelper1.COLUMN_NAME);
                int descriptionIndex = cursor.getColumnIndex(DatabaseHelper1.COLUMN_DESCRIPTION);
                int priceIndex = cursor.getColumnIndex(DatabaseHelper1.COLUMN_PRICE);

                if (idIndex != -1 && nameIndex != -1 && descriptionIndex != -1 && priceIndex != -1) {
                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    String description = cursor.getString(descriptionIndex);
                    double price = cursor.getDouble(priceIndex);

                    FoodItem foodItem = new FoodItem(name, description, price);
                    foodItem.setId(id);
                    foodItemList.add(foodItem);
                } else {
                    Log.e("Cursor Error", "One or more column indices are invalid");
                }
            } while (cursor.moveToNext());
        } else {
            Log.e("Cursor Error", "Cursor is empty");
        }

        cursor.close();
        adapter.notifyDataSetChanged();
    }
}
