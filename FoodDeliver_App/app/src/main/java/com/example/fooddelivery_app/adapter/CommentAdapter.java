package com.example.fooddelivery_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.model.Comment.CommentDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private final Context context;
    private final List<CommentDto> commentList;

    public CommentAdapter(Context context, List<CommentDto> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        CommentDto comment = commentList.get(position);
        holder.userName.setText(comment.getUserName());
        holder.commentContent.setText(comment.getContent());
        holder.commentTime.setText(formatDate(comment.getCommentDate()));
        holder.ratingBar.setRating(comment.getVote());

        // Load user profile picture
        Glide.with(context)
                .load(comment.getUserPicture()) // Assuming CommentDto has a profile picture URL field
                .placeholder(R.drawable.baseline_account_circle_24) // Placeholder if image is null
                .into(holder.userProfilePicture);
//        holder.deleteCommentButton.setOnClickListener(v -> {
//            if (context instanceof FoodDetailActivity) {
//                ((FoodDetailActivity) context).deleteComment(comment.getId(), position);
//            }
//        });

    }
    private String formatDate(String isoDate) {
        // Create a SimpleDateFormat to parse the incoming ISO date
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
        // Create a SimpleDateFormat for the desired output format
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault());

        try {
            // Parse the ISO date string into a Date object
            Date date = isoFormat.parse(isoDate);
            // Format the Date object into the desired output string
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return isoDate; // Return the original ISO date in case of parsing error
        }
    }
    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView userName, commentContent, commentTime;
        RatingBar ratingBar;
        ImageView userProfilePicture;
//        Button deleteCommentButton;
        public CommentViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            commentContent = itemView.findViewById(R.id.commentContent);
            commentTime = itemView.findViewById(R.id.commentTime);
            ratingBar = itemView.findViewById(R.id.ratingBar1);
            userProfilePicture = itemView.findViewById(R.id.userProfilePicture);
//            deleteCommentButton = itemView.findViewById(R.id.deleteButton);
        }

    }
}
