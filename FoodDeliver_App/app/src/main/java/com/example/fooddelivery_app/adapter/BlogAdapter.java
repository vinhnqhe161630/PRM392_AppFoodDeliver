package com.example.fooddelivery_app.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.model.Comment.Blog;

import java.io.File;
import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder> {
    private final List<Blog> blogList;
    private final Context context;
    public BlogAdapter(Context context, List<Blog> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog, parent, false);
        return new BlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        Blog blog = blogList.get(position);

        holder.blogTitle.setText(blog.getTitle());
        holder.blogAuthor.setText(blog.getAuthor());
        holder.blogDate.setText(blog.getDate());
        holder.blogSummary.setText(blog.getSummary());

        String imageUrl = blog.getImageUrl();


            // Load from URL
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.baseline_image_24) // Placeholder image while loading
                    .error(R.drawable.baseline_image_24) // Error image if there's an issue loading the image
                    .into(holder.blogImage);

    }


    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        ImageView blogImage;
        TextView blogTitle, blogAuthor, blogDate, blogSummary;

        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            blogImage = itemView.findViewById(R.id.blog_image);
            blogTitle = itemView.findViewById(R.id.blog_title);
            blogAuthor = itemView.findViewById(R.id.blog_author);
            blogDate = itemView.findViewById(R.id.blog_date);
            blogSummary = itemView.findViewById(R.id.blog_summary);
        }
    }
}
