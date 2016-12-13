package edu.uvm.bazaar;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import edu.uvm.loginregister.R;

public class DeveloperAreaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_area);

        String username ="";
//        String devStatus ="";
        String userId ="";
        ArrayList<String> devGames = new ArrayList<>();
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            username = extra.getString("username");
            userId = extra.getString("UserId");
//            devStatus = extra.getString("devStatus");
            devGames = extra.getStringArrayList("devGames");

        }

        final TextView etUsername = (TextView) findViewById(R.id.usrnme);
        etUsername.setText(username);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);
//        final TextView subgroups = (TextView) findViewById(R.id.subGroups);
        final TextView subgames = (TextView) findViewById(R.id.devGames);
        final ListView subgameslist = (ListView) findViewById(R.id.listDevGames);
//        final LinearLayout subgroupslist = (LinearLayout) findViewById(R.id.subgroupslist);
        final Button gamelist = (Button) findViewById(R.id.devGameList);
        final Button invite = (Button) findViewById(R.id.invite);
//        final Button devel = (Button) findViewById(R.id.userDevelBtn);
//        IprofilePic = (ImageView) findViewById(R.id.profilepic);
        final String finalUsername = username;
        final String finalUserId = userId;
        subgameslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, View v,
                                    final int position, long id) {
                final String gameTitle = (String)parent.getItemAtPosition(position);
                String imageId="";
                switch (gameTitle){
                    case "No Man Sky":
                        imageId = "nomansky";
                        break;
                    case "League of Legends":
                        imageId = "league";
                        break;
                    case "Civilization Beyond Earth":
                        imageId = "civ";
                        break;
                    case "World of Warcraft":
                        imageId = "wow";
                }
                Intent intent = new Intent(DeveloperAreaActivity.this,EmptyGameProfile.class);
                intent.putExtra("imageId",imageId);
                intent.putExtra("userId", finalUserId);
                intent.putExtra("TITLE",gameTitle);
                startActivity(intent);


            }
        });
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,devGames);

        subgameslist.setAdapter(adapter2);
        gamelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(DeveloperAreaActivity.this, Games.class);
                registerIntent.putExtra("username", finalUsername);
                registerIntent.putExtra("userId", finalUserId);
                DeveloperAreaActivity.this.startActivity(registerIntent);
            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
             String game = "";
            String getuser = "";
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DeveloperAreaActivity.this);
                builder.setTitle("Enter the username");


// Set up the input
//                LinearLayout layout = new LinearLayout(DeveloperAreaActivity.this);
//                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText user = new EditText(DeveloperAreaActivity.this);
                user.setHint("username");
                builder.setView(user);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getuser = user.getText().toString();
                        AlertDialog.Builder gameB = new AlertDialog.Builder(DeveloperAreaActivity.this);
                        gameB.setTitle("Enter the game you would like to invite them to");
                        final EditText input = new EditText(DeveloperAreaActivity.this);
                        input.setHint("Game Name");
                        gameB.setView(input);
                        gameB.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                              game = input.getText().toString();
                                final Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        if (!response.isEmpty()) {
                                            AlertDialog.Builder rspns = new AlertDialog.Builder(DeveloperAreaActivity.this);
                                            rspns.setMessage("User "+getuser+" has been invited to the developer group for the game "+game).setNegativeButton("Ok", null).create().show();
                                        }
                                    }
                                };
                                NewDevUserRequest thread = new NewDevUserRequest(game, getuser, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(DeveloperAreaActivity.this);
                                queue.add(thread);

                            }
                        });
                        gameB.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        gameB.show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
//                layout.addView(input);

//                layout.addView(user);
//                builder.setView(layout);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                builder.setView(input);

// Set up the buttons
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        final Response.Listener<String> responseListener = new Response.Listener<String>() {
//
//                            @Override
//                            public void onResponse(String response) {
//                                if (!response.isEmpty()) {
//                                    AlertDialog.Builder rspns = new AlertDialog.Builder(DeveloperAreaActivity.this);
//                                    rspns.setMessage("User "+getuser+" has been invited to the developer group for the game "+game).setNegativeButton("Ok", null).create().show();
//                                }
//                            }
//                        };
//                         NewDevUserRequest thread = new NewDevUserRequest(game, getuser, responseListener);
//                        RequestQueue queue = Volley.newRequestQueue(DeveloperAreaActivity.this);
//                        queue.add(thread);
//                    }
//                });


            }
        });
    }
}
