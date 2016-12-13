package edu.uvm.bazaar;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.uvm.loginregister.R;

public class UserAreaActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE = 1;
    final private int MY_PERMISSIONS_REQUEST_PHOTOS = 111;
    ImageView IprofilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<String> threadPosts = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        String username ="";
        String devStatus ="";
        String userId ="";
        ArrayList<String> subGames = new ArrayList<>();
        ArrayList<String> subThreads = new ArrayList<>();
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            username = extra.getString("Username");
            userId = extra.getString("UserId");
            devStatus = extra.getString("devStatus");
            subThreads = extra.getStringArrayList("subThreads");
            subGames = extra.getStringArrayList("subGames");
        }

        final TextView etUsername = (TextView) findViewById(R.id.usrnme);
        etUsername.setText(username);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);
        final TextView subgroups = (TextView) findViewById(R.id.subGroups);
        final TextView subgames = (TextView) findViewById(R.id.devGames);
//        final ListView subgameslist = (ListView) findViewById(R.id.subGamesList);
        final ListView subgroupslist = (ListView) findViewById(R.id.subGroupList);
        final Button gamelist = (Button) findViewById(R.id.btGames);
//        final Button devel = (Button) findViewById(R.id.userDevelBtn);
        IprofilePic = (ImageView) findViewById(R.id.profilepic);
        final String finalUsername = username;

        //THIS IS WHERE THERE NEEDS TO BE A CALL TO THE DATABASE TO PULL ALL OF THE GAMES AND THREADS THAT A USER IS SUBSCRIBED TO.
//        if (devStatus==("U")){
//            devel.setVisibility(View.VISIBLE);
//            final String finalDevStatus = devStatus;
//            final String finalUsername1 = username;
//            final String finalUserId = userId;
//            devel.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View view) {
//                    Intent userDevel = new Intent(UserAreaActivity.this, UserDeveloperActivity.class);
//                    userDevel.putExtra("username", finalUsername1);
//                    userDevel.putExtra("devStatus", finalDevStatus);
//                    userDevel.putExtra("userId", finalUserId);
//                }
//            });
//            //SET BUTTON IN USER PAGE THAT LINKS TO DEVELOPMENT PAGE
//        }
        final ArrayList<String> finalSubThreads = subThreads;
        final ArrayList<String> finalSubThreads1 = subThreads;
        final String finalUserId = userId;
        subgroupslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, View v,
                                    final int position, long id) {
                final String threadTitle = (String)parent.getItemAtPosition(position);
                //Toast.makeText(main.this, "" + position,
                //Toast.LENGTH_SHORT).show();
                String url = "http://www.uvm.edu/~ertait/gameforum.php?threadTitle="+threadTitle;
                RequestQueue threadQueue = Volley.newRequestQueue(UserAreaActivity.this);
                JsonArrayRequest threadRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

//
//                ArrayList<String> list = new ArrayList<String>();

                        try {
                            JSONObject jsonResponse = null;
                            threadPosts.clear();
                            if (response.length() > 0) {
                                for (int i = 0; i < response.length(); i++) {
                                    jsonResponse = response.getJSONObject(i);
                                    String u = (String) jsonResponse.getString("fldUsername");
                                    String r = (String) jsonResponse.getString("txtThread");
                                    String c = u+": "+r;
                                    threadPosts.add(c);
                                }
//                                AlertDialog.Builder rspns = new AlertDialog.Builder(ThreadList.this);
//                                rspns.setMessage(comments.toString()+response.toString()).setNegativeButton("Retry", null).create().show();

//                        JSONArray jsonArray = (JSONArray)jsonResponse;
//                        if (response != null) {
//                            int len = response.length();
//                            for (int i=0;i<len;i++){
//                                try {
//                                    values.add(response.get(i).toString());
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }


//                        }
                            }
                            Intent intent = new Intent(UserAreaActivity.this, Thread.class);

                            intent.putExtra("userId", finalUserId);
//                            intent.putExtra("gameId", gameId);
                            intent.putExtra("comments", threadPosts);
                            intent.putExtra("threadTitle",threadTitle);


                            ;
                            startActivity(intent);
//                    AlertDialog.Builder rspns = new AlertDialog.Builder(EmptyGameProfile.this);
//                    rspns.setMessage(response.toString()+jsonResponse.getString("txtThreadName").getClass().getName()+"values:"+values).setNegativeButton("Retry", null).create().show();
                            if (jsonResponse != null){
//                        JSONArray array = (JSONArray)jsonResponse;
//                                for (int i=0;i<response.length();i++){
////                                    values.add(response.getString(i));

//
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder rspns = new AlertDialog.Builder(UserAreaActivity.this);
                        rspns.setMessage(error.toString()).setNegativeButton("Retry", null).create().show();
                    }
                });
                threadQueue.add(threadRequest);


            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,subThreads);

        subgroupslist.setAdapter(adapter);
        final String finalUserId2 = userId;
//        subgameslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(final AdapterView<?> parent, View v,
//                                    final int position, long id) {
//                final String gameTitle = (String)parent.getItemAtPosition(position);
//                String imageId="";
//                switch (gameTitle){
//                    case "No Man Sky":
//                        imageId = "nomansky";
//                        break;
//                    case "League of Legends":
//                        imageId = "league";
//                        break;
//                    case "Civilization Beyond Earth":
//                        imageId = "civ";
//                        break;
//                    case "World of Warcraft":
//                        imageId = "wow";
//                }
//                Intent intent = new Intent(UserAreaActivity.this,EmptyGameProfile.class);
//                intent.putExtra("imageId",imageId);
//                intent.putExtra("userId", finalUserId2);
//                intent.putExtra("TITLE",gameTitle);
//                startActivity(intent);
//
//
//            }
//        });
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,subGames);
//
//        subgameslist.setAdapter(adapter2);
        final String finalUserId1 = userId;
        gamelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(UserAreaActivity.this, Games.class);
                registerIntent.putExtra("username", finalUsername);
                registerIntent.putExtra("userId", finalUserId1);
                UserAreaActivity.this.startActivity(registerIntent);
            }
        });
        IprofilePic.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        //access phone photo library and input a photo
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(UserAreaActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(UserAreaActivity.this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(UserAreaActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_PHOTOS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_PHOTOS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    @Override //this has to get fixed becuase it is a newer model so there needs to be a new permissions file
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();

           IprofilePic.setImageURI(selectedImage);

        }

    }
}