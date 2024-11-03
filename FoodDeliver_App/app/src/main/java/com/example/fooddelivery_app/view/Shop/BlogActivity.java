package com.example.fooddelivery_app.view.Shop;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.BlogAdapter;
import com.example.fooddelivery_app.model.Comment.Blog;
import com.example.fooddelivery_app.view.MainActivity;
import com.example.fooddelivery_app.view.Order.CartActivity;
import com.example.fooddelivery_app.view.Order.OrderListActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class BlogActivity extends AppCompatActivity {
    public static List<Blog> blogList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);


        RecyclerView recyclerView = findViewById(R.id.productRecyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        blogList.add(new Blog(
                "https://scontent.fhan3-5.fna.fbcdn.net/v/t39.30808-6/461928819_122109336230540016_1657058200134849741_n.png?_nc_cat=109&ccb=1-7&_nc_sid=cc71e4&_nc_eui2=AeEu_MYeHtwkf1mahXYOhZdG3peGevkz_Xvel4Z6-TP9e_Fal2T2-Ej0FENbEQ80x6j45J_k1fjILkctW0TjmjNE&_nc_ohc=afnJ1kkwQ_UQ7kNvgGUi8gJ&_nc_zt=23&_nc_ht=scontent.fhan3-5.fna&_nc_gid=AFi0VwQxaIuQYkby3VzMlk6&oh=00_AYAcx0GdUECGYquHYQ-HVPM9lk0AhOmdN5Thjz_zXiNdig&oe=672D5DCB",
                "Bánh Gai Thế Anh - Món ăn truyền thống không thể thiếu",
                "Nguyễn Xuân Phi",
                "Xuất bản ngày 5 tháng 10, 2024",
                "Bánh gai Thế Anh với lớp bánh gai dẻo, nhân đậu thơm ngon khiến tôi không thể cưỡng lại mà ăn ngấu nghiến"
        ));
        blogList.add(new Blog(
                "https://danviet.mediacdn.vn/296231569849192448/2023/2/25/1677229616-9522phothintokyonguoidanongnhatphailongphotu04081224082019-1677301320607-16773013208941492638242.jpg",
                "Phở Thìn - Tinh hoa phở bò Hà Nội",
                "Nguyễn Văn Hùng",
                "Xuất bản ngày 1 tháng 10, 2024",
                "Phở Thìn trên phố Lò Đúc đã trở thành huyền thoại với hương vị phở bò đậm đà, thơm ngọt. Khám phá nét đặc sắc của món phở Hà Nội trứ danh."
        ));

        blogList.add(new Blog(
                "https://i.ytimg.com/vi/D6cVeKmYfWY/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLDQTdjT0uB3RgUJr7joKG38X539uA",
                "Bún chả Hương Liên - Thưởng thức bữa ăn của Tổng thống Obama",
                "Trần Quang Minh",
                "Xuất bản ngày 3 tháng 10, 2024",
                "Bún chả Hương Liên nổi tiếng sau khi Tổng thống Obama ghé thăm. Một trải nghiệm không thể bỏ qua khi đến Hà Nội."
        ));



        blogList.add(new Blog(
                "https://cdn.tgdd.vn/Files/2020/03/31/1245702/cach-lam-cha-ca-la-vong-cha-ca-lang-thom-ngon-c-760x367.jpg",
                "Chả cá Lã Vọng - Hương vị đậm chất Hà Thành",
                "Phạm Quốc Khánh",
                "Xuất bản ngày 7 tháng 10, 2024",
                "Chả cá Lã Vọng là một trong những món ăn độc đáo nhất Hà Nội. Thưởng thức cá nướng cùng hành và thì là, tạo nên vị ngon khó quên."
        ));

        blogList.add(new Blog(
                "https://cdn.tuoitre.vn/zoom/700_390/471584752817336320/2023/12/12/kem-trang-tien-17023991455002141453790-89-0-508-800-crop-17023992521351227755151.jpg",
                "Kem Tràng Tiền - Thức quà mát lạnh giữa lòng thủ đô",
                "Đặng Minh Anh",
                "Xuất bản ngày 10 tháng 10, 2024",
                "Kem Tràng Tiền gắn liền với tuổi thơ của nhiều thế hệ người Hà Nội. Vị kem mát lạnh, thơm béo, là món ăn lý tưởng cho những ngày nắng nóng."
        ));

        blogList.add(new Blog(
                "https://i.ytimg.com/vi/Xb-s5pPlbbc/maxresdefault.jpg",
                "Bún riêu cua Hàng Bông - Hương vị dân dã, bình dị",
                "Nguyễn Thị Thảo",
                "Xuất bản ngày 12 tháng 10, 2024",
                "Bún riêu cua trên phố Hàng Bông mang lại vị ngon ngọt từ cua đồng, ăn kèm với rau sống tạo nên món ăn thanh mát và bổ dưỡng."
        ));

        blogList.add(new Blog(
                "https://cdn-i.vtcnews.vn/resize/th/upload/2024/10/02/2017-10-07-12030489.jpg",
                "Mì vằn thắn Bình Tây - Hương vị Hoa giữa lòng Hà Nội",
                "Vũ Trường Sơn",
                "Xuất bản ngày 14 tháng 10, 2024",
                "Quán mì vằn thắn Bình Tây mang phong vị Trung Hoa đến với Hà Nội. Món ăn này có vị thanh ngọt, đậm đà từ xương và hải sản."
        ));

        blogList.add(new Blog(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYbHFNOdYob_Y9qfQI3gOLQkbOUcwPnpttmg&s",
                "Nem chua rán Hàng Bông - Món ăn vặt hấp dẫn",
                "Đỗ Thanh Tùng",
                "Xuất bản ngày 16 tháng 10, 2024",
                "Nem chua rán Hàng Bông là điểm đến yêu thích của giới trẻ Hà Nội. Món nem thơm ngon, giòn tan, ăn kèm cùng tương ớt cay nồng."
        ));

        blogList.add(new Blog(
                "https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/longg/menu%20l%E1%BA%A9u%20phan/menu-l%E1%BA%A9u-phan-1.jpg",
                "Lẩu Phan - Địa điểm ăn uống lý tưởng cho nhóm bạn",
                "Bùi Thị Trang",
                "Xuất bản ngày 18 tháng 10, 2024",
                "Lẩu Phan nổi tiếng với giá cả hợp lý và không gian rộng rãi. Thực đơn phong phú, lẩu ngon và đa dạng thích hợp cho buổi gặp mặt bạn bè."
        ));

        blogList.add(new Blog(
                "https://mms.img.susercontent.com/vn-11134513-7r98o-lstq2p6mzhdw98@resize_ss1242x600!@crop_w1242_h600_cT",
                "Xôi Yến - Bữa sáng đặc sản của người Hà Nội",
                "Lê Văn Quý",
                "Xuất bản ngày 20 tháng 10, 2024",
                "Xôi Yến trên phố Nguyễn Hữu Huân phục vụ món xôi nóng hổi với nhiều lựa chọn như thịt kho, pate, trứng chiên - một bữa sáng đầy đủ dinh dưỡng."
        ));


        BlogAdapter adapter = new BlogAdapter(this, blogList);
        recyclerView.setAdapter(adapter);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
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
        FloatingActionButton addBlogButton = findViewById(R.id.add_blog);
        addBlogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Add Blog Activity
                Intent intent = new Intent(BlogActivity.this, CreateBlogActivity.class);
                startActivity(intent);
            }
        });
    }
}
