package edu.uvm.bazaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import edu.uvm.loginregister.R;

public class DeveloperAreaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_area);

        String username ="";
        String devStatus ="";
        String userId ="";
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            username = extra.getString("username");
            userId = extra.getString("UserId");
            devStatus = extra.getString("devStatus");

        }

        final TextView etUsername = (TextView) findViewById(R.id.usrnme);
        etUsername.setText(username);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);
//        final TextView subgroups = (TextView) findViewById(R.id.subGroups);
        final TextView subgames = (TextView) findViewById(R.id.devGames);
        final GridLayout subgameslist = (GridLayout) findViewById(R.id.subGamesList);
//        final LinearLayout subgroupslist = (LinearLayout) findViewById(R.id.subgroupslist);
        final Button gamelist = (Button) findViewById(R.id.devGameList);
//        final Button devel = (Button) findViewById(R.id.userDevelBtn);
//        IprofilePic = (ImageView) findViewById(R.id.profilepic);
        final String finalUsername = username;
        final String finalUserId = userId;
        gamelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(DeveloperAreaActivity.this, Games.class);
                registerIntent.putExtra("username", finalUsername);
                registerIntent.putExtra("userId", finalUserId);
                DeveloperAreaActivity.this.startActivity(registerIntent);
            }
        });
    }
}
