package com.example.retrofit2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycleview_adapter extends RecyclerView.Adapter<recycleview_adapter.ViewHolder> {
    private ArrayList<recycleview_list> recycleviewL_list;
    private Context context;

    public recycleview_adapter(ArrayList<recycleview_list> recycleviewL_list, Context context) {
        this.recycleviewL_list = recycleviewL_list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.imageView.setImageResource(recycleviewL_list.get(position).getImage());
        holder.textView.setText(recycleviewL_list.get(position).getText());
        //holder.costView.setText(recycleviewL_list.get(position).getCost());
    }

    @Override
    public int getItemCount() {
        return recycleviewL_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView costView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            costView=itemView.findViewById(R.id.costView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();

                    // check if item still exists
                    if (pos != RecyclerView.NO_POSITION) {
                        recycleview_list clickedDataItem = recycleviewL_list.get(pos);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getText(), Toast.LENGTH_SHORT).show();
                        //displayNotification(clickedDataItem.getText(),clickedDataItem.getImage());
                    }
                }
            });
        }
    }

//    private static final String CHANNEL_ID = "simplified_coding";
//    private void displayNotification(String tv,int imageResource) {
//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imageResource);
//
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                .setContentTitle("Title push notification")
//                .setContentText("You click on"+tv)
//                .setSmallIcon(R.drawable.money)
//                .setLargeIcon(bitmap)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//        NotificationManagerCompat mNotificationManagerCompat = NotificationManagerCompat.from(context);
//
//        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mNotificationManagerCompat.notify(1, mBuilder.build());
//    }
}
