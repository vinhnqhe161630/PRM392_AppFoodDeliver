package com.example.fooddelivery_app.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.model.Order.Order;
import com.example.fooddelivery_app.view.Order.OrderDetailsActivity;
import com.example.fooddelivery_app.view.Order.OrderListActivity;

import java.util.List;
import java.util.UUID;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.orderItemName.setText(order.getShopname());
        holder.orderItemPrice.setText(order.getTotalAmount()+" VND");
        holder.orderStatus.setText(order.getStatus());
        holder.orderDate.setText(order.getOrderDate().toString());
        //holder.orderID.setText(order.getId().toString());
        // Set other order item details here
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderItemName;
        TextView orderItemPrice;
        TextView orderStatus;
        TextView orderDate;
        //TextView orderID;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderItemName = itemView.findViewById(R.id.tvShopName);
            orderItemPrice = itemView.findViewById(R.id.tvTotalAmount);
            orderDate = itemView.findViewById(R.id.tvOrderDate);
            //orderID = itemView.findViewById(R.id.tvOrderId);
            orderStatus = itemView.findViewById(R.id.tvStatus);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), OrderDetailsActivity.class);
                // Assuming you have a method to get the orderId from the current item
                UUID orderId = getOrderId();
                intent.putExtra("ORDER_ID", orderId.toString());
                v.getContext().startActivity(intent);
            });
        }

        private UUID getOrderId() {
            // Implement this method to return the orderId of the current item
            return UUID.randomUUID(); // Replace with actual orderId retrieval logic
        }
    }

}
