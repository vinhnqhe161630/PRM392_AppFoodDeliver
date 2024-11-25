package com.example.fooddelivery_app.view.Shop;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.model.Comment.Blog;
import com.example.fooddelivery_app.view.MainActivity;
import com.example.fooddelivery_app.view.Order.CartActivity;
import com.example.fooddelivery_app.view.Order.OrderListActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;

public class CreateBlogActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText etBlogTitle, etBlogContent;
    private ImageView ivBlogImagePreview;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogadd);

        etBlogTitle = findViewById(R.id.etBlogTitle);
        etBlogContent = findViewById(R.id.etBlogContent);
        ivBlogImagePreview = findViewById(R.id.ivBlogImagePreview);
        Button btnUploadImage = findViewById(R.id.btnUploadImage);
        Button btnSaveBlog = findViewById(R.id.btnSaveBlog);

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        btnSaveBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBlog();
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_blog);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_cart:
                    // Open OrderActivity when Order menu item is clicked
                    Intent cartIntent = new Intent(this, CartActivity.class);
                    startActivity(cartIntent);
                    finish();
                    return true;
                case R.id.navigation_More:
                    // Open OrderActivity when Order menu item is clicked
                    Intent orderIntent = new Intent(this, OrderListActivity.class);
                    startActivity(orderIntent);
                    finish();
                    return true;
                case R.id.navigation_blog:
                    // Open CartActivity when Cart menu item is clicked
                    Intent shopIntent = new Intent(this, BlogActivity.class);
                    startActivity(shopIntent);
                    finish();
                    return true;
                case R.id.navigation_Rank:
                    // Open CartActivity when Cart menu item is clicked
                    Intent rankIntent = new Intent(this, ShopVotedActivity.class);
                    startActivity(rankIntent);
                    finish();
                    return true;
                default:
                    Intent homeIntent = new Intent(this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                    return true;
                // Handle other menu items here

            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            ivBlogImagePreview.setImageURI(imageUri);
            String imagePath = getRealPathFromURI(this, imageUri);
        }
    }
    private String getRealPathFromURI(Context context, Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
    private void saveBlog() {
        String title = etBlogTitle.getText().toString().trim();
        String content = etBlogContent.getText().toString().trim();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        // Create a new Blog object
        Blog newBlog = new Blog("https://nemluji.vn/wp-content/uploads/2024/09/1699436344-446-thumbnail-width640height480.jpg",title,"Vu Bao"  , "Xuất bản ngày 4 tháng 11, 2024", content);

        // Add it to the existing blogList
        BlogActivity.blogList.add(0, newBlog);

        Intent intent = new Intent(this, BlogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        Toast.makeText(this, "Blog saved successfully!", Toast.LENGTH_SHORT).show();
        finish(); // Close the activity after saving
    }
}
