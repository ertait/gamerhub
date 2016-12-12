package edu.uvm.bazaar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import edu.uvm.loginregister.R;

public class Games extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);


        final GridView gridview = (GridView) findViewById(R.id.gameview);
        gridview.setAdapter(new ImageAdapter(this));
        String username ="";
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            username = extra.getString("username");
        }
        final String finalUsername = username;
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public String title="";
            public String imageId = "";
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
//                AlertDialog.Builder check = new AlertDialog.Builder(Games.this);
//                check.setMessage("position"+position).setNegativeButton("Retry", null).create().show();
                int t = position;
                switch (t){
                    case 0:
                        title = "League of Legends";
                        imageId = "league";
                        break;
                    case 1:
                        title = "World of Warcraft";
                        imageId = "wow";
                        break;
                    case 2:
                        title = "Civilization Beyond Earth";
                        imageId = "civ";
                        break;
                    case 3:
                        title = "No Man Sky";
                        imageId = "nomansky";
                }

                // needs to get image from clicked element, find the game name in the database and pass the game name into the new view
                //String title = (String) gridview.getItemAtPosition(position);
                //String title = "Call of Duty 4";
                //Toast.makeText(main.this, "" + position,
                //Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Games.this, EmptyGameProfile.class);
                intent.putExtra("TITLE",title);
                intent.putExtra("username", finalUsername);
                intent.putExtra("imageId",imageId );
                startActivity(intent);

            }
        });
    }
}
