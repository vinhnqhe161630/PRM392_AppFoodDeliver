package com.example.fooddelivery_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.example.fooddelivery_app.model.Order.Order;
import com.example.fooddelivery_app.view.Order.OrderDetailsActivity;
import com.example.fooddelivery_app.view.Order.OrderListActivity;

import java.util.List;
import java.util.UUID;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;
    private Context context;


    public OrderAdapter(List<Order> orderList,Context context) {
        this.orderList = orderList;
        this.context=context;
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
        holder.currentOrder = order; // Set the current order
        holder.orderItemName.setText(order.getShopname());
        holder.orderItemPrice.setText(order.getTotalAmount() + " VND");
        holder.orderStatus.setText(order.getStatus());
        holder.orderDate.setText(order.getOrderDate().toString());
        holder.orderID.setText(order.getId().toString());
        Glide.with(context).load(order.getShopImg()).into(holder.productImageView);

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
        TextView orderID;
        Order currentOrder;
        ImageView productImageView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderItemName = itemView.findViewById(R.id.tvShopName);
            orderItemPrice = itemView.findViewById(R.id.tvTotalAmount);
            orderDate = itemView.findViewById(R.id.tvOrderDate);
            orderID = itemView.findViewById(R.id.tvOrderId);
            orderStatus = itemView.findViewById(R.id.tvStatus);
            productImageView=itemView.findViewById(R.id.imgShop);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), OrderDetailsActivity.class);
                UUID orderId = getOrderId();
                intent.putExtra("ORDER_ID", orderId.toString());
                v.getContext().startActivity(intent);
            });
        }

        private UUID getOrderId() {
            return currentOrder.getId();
        }
    }


}
