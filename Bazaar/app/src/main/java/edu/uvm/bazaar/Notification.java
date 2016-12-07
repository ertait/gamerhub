package edu.uvm.bazaar;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;



public class Notification extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        PendingIntent pIntent = PendingIntent.getActivity(Notification.this, 0, intent, 0);
                        android.app.Notification noti = new android.app.Notification.Builder(Notification.this)
                                .setTicker("Ticker Title")
                                .setContentTitle("Content Title")
                                .setContentText("Content Text here it goes")
                                .setSmallIcon(R.drawable.common_google_signin_btn_text_dark)
                                .setContentIntent(pIntent).getNotification();
                        noti.flags = android.app.Notification.FLAG_AUTO_CANCEL;
                        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        nm.notify(0, noti);
                    }
                }
        );
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}


// This is just some sample code I found online that I could potentially use. Don't worry about this. //

/*
public class MainActivity extends Activity implements OnClickListener{

    private Button b;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_notification);
        b=(Button)findViewById(R.id.button);
        b.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu screen) {
        getMenuInflater().inflate(R.menu.content_notification, screen);
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification noti = new Notification.Builder(this)
                .setTicker("Ticker Title")
                .setContentTitle("Content Title")
                .setContentText("Notification content.")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pIntent).getNotification();
        noti.flags=Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti);
    }
}
*/