package com.example.fooddelivery_app.view.Order;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.model.Order.Order;
import com.example.fooddelivery_app.viewmodel.Order.OrderListViewModel;

import java.util.List;
import java.util.UUID;

public class OrderListActivity extends AppCompatActivity {
    private OrderListViewModel orderViewModel;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_list);
        listView = findViewById(R.id.listView);
        orderViewModel = new ViewModelProvider(this).get(OrderListViewModel.class);

        UUID userId = UUID.fromString(orderViewModel.getUserIdFromToken(this));

        orderViewModel.getOrderByUserId(userId).observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                if (orders != null) {
                    // Convert Order list to a displayable format (e.g., names or a custom adapter)
                    ArrayAdapter<Order> adapter = new ArrayAdapter<>(OrderListActivity.this,
                            android.R.layout.simple_list_item_1, orders);
                    listView.setAdapter(adapter);
                }
            }
        });

    }
}