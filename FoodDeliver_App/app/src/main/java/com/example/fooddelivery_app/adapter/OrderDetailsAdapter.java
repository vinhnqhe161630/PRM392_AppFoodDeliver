package com.example.fooddelivery_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.model.Order.OrderDetails;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder> {

    private List<OrderDetails> orderDetailsList;

    public OrderDetailsAdapter(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    @NonNull
    @Override
    public OrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productcart, parent, false);
        return new OrderDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsViewHolder holder, int position) {
        OrderDetails orderDetails = orderDetailsList.get(position);
        holder.orderDetailsItemName.setText(orderDetails.getFoodName());
        holder.orderDetailsItemPrice.setText(orderDetails.getPrice()+" VND");
        holder.quantityTextView.setText(orderDetails.getQuantity()+" ");

        // Set other orderDetails item details here
    }

    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }

    public static class OrderDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView orderDetailsItemName;
        TextView orderDetailsItemPrice;
        TextView quantityTextView;

        public OrderDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDetailsItemName = itemView.findViewById(R.id.tvProductName);
            orderDetailsItemPrice= itemView.findViewById(R.id.productPriceTextView);
            quantityTextView= itemView.findViewById(R.id.quantityTextView);

        }
    }
}
