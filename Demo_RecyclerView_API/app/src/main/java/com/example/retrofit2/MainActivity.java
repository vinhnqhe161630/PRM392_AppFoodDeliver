package com.example.retrofit2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<User> listUser= new ArrayList<User>();
    ArrayList<recycleview_list> recycleviewLists;
    RecyclerView recyclerView;
    private recycleview_adapter recycleview_adapter;
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "simplified_coding";
    private static final String CHANNEL_NAME = "Simplified Coding";
    private static final String CHANNEL_DESC = "Simplified Coding Notifications";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView= findViewById(R.id.recyclerVIew);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recycleviewLists=new ArrayList<recycleview_list>();
        ApiService apiService = RetrofitClient.getClient("http://10.0.2.2:7121/api/").create(ApiService.class);
        Call<List<User>> call=apiService.getUsers();
        TextView txt=findViewById(R.id.Title_);


        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body();
                    listUser.clear(); // Clear previous data if necessary

                    for (User user : users) {
                        listUser.add(user);  // Add users from API response into listUser
                        recycleviewLists.add(new recycleview_list(R.drawable.phone, user.getUsername(), "120$")); // Change `user.getName()` to the actual name
                    }

                    // Notify adapter about the new data
                    recycleview_adapter.notifyDataSetChanged(); // Notify adapter about the changes
                    txt.setText(users.size() + " users fetched successfully");
                    // Handle the success case
                } else {
                    // Handle the error cas
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                // Handle failure
                recycleviewLists.add(new recycleview_list(R.drawable.phone,t.getMessage(),"120$"));

            }
        });
         recycleview_adapter =new recycleview_adapter(recycleviewLists,this);
        recyclerView.setAdapter(recycleview_adapter);
//        Call<User> call=apiService.getUser(1);
//        TextView txt=findViewById(R.id.Title_);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.isSuccessful()) {
//                    User users = response.body();
//                    txt.setText(users.getTitle());
//                    // Handle the success case
//                } else {
//                    // Handle the error case
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                // Handle failure
//            }
//        });
        if(!listUser.isEmpty())
        {
            txt.setText(listUser.size()+"oke");
        }
        else{
            txt.setText(" not oke");

        }
    }
}