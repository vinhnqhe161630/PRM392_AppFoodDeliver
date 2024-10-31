package com.example.fooddelivery_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.model.Comment.CommentDto;


import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private final Context context;
    private final List<CommentDto> commentList;

    public CommentAdapter(Context context, List<CommentDto> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        CommentDto comment = commentList.get(position);
        holder.userNameTextView.setText(comment.getUserName());
        holder.contentTextView.setText(comment.getContent());
        holder.voteTextView.setText(String.valueOf(comment.getVote()));
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView, contentTextView, voteTextView;

        public CommentViewHolder(View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            voteTextView = itemView.findViewById(R.id.voteTextView);
        }
    }
}
