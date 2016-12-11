package edu.uvm.bazaar;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class appNotification extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    public void createNotification(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("Notification!")
                .setContentText("This is my first notification!");

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Etiam hendrerit risus ut congue feugiat. Donec rutrum tristique purus, at tempus ipsum pharetra eget. " +
                "Ut tristique aliquet elementum. Sed hendrerit quis sapien a mattis. " +
                "Pellentesque interdum neque a felis mattis finibus. ");

        builder.setStyle(bigText);

        Intent resultIntent = new Intent(this, ResultNotification.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, notification);
    }
}
