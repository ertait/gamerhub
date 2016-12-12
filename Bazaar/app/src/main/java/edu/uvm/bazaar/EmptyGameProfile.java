package edu.uvm.bazaar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.uvm.loginregister.R;

public class EmptyGameProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_game_profile);
        RequestQueue getGame = Volley.newRequestQueue(EmptyGameProfile.this);
        final TextView titleSpace = (TextView) findViewById(R.id.title);
        final TextView description = (TextView) findViewById(R.id.description);
        final ImageView imageView = (ImageView) findViewById(R.id.gamePicture);
        String title = "";
        String username ="";
        String imageId ="";
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            title = extra.getString("TITLE");
            username = extra.getString("username");
            imageId = extra.getString("imageId");
        }

        //title = "new title";
        Integer newWidth = 400;
        Integer newHeight = 200;
        titleSpace.setText(title);
        switch (imageId){
            case "league":
//                imageView.setImageResource(R.drawable.league);
                Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.league);
                Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 600, 300, true);
                imageView.setImageBitmap(bMapScaled);
                break;
            case "wow":
                Bitmap bMap1 = BitmapFactory.decodeResource(getResources(), R.drawable.wow);
                Bitmap bMapScaled1 = Bitmap.createScaledBitmap(bMap1, 600, 400, true);
                imageView.setImageBitmap(bMapScaled1);
                break;
            case "civ":
                Bitmap bMap2 = BitmapFactory.decodeResource(getResources(), R.drawable.civ);
                Bitmap bMapScaled2 = Bitmap.createScaledBitmap(bMap2, 700, 400, true);
                imageView.setImageBitmap(bMapScaled2);
                break;
            case "nomansky":
                Bitmap bMap3 = BitmapFactory.decodeResource(getResources(), R.drawable.nomansky);
                Bitmap bMapScaled3 = Bitmap.createScaledBitmap(bMap3, 400, 400, true);
                imageView.setImageBitmap(bMapScaled3);
                break;
        }

//        final String finalTitle = title;
//        final Runnable mUpdateUITimerTask = new Runnable() {
//            public void run() {
//                // do whatever you want to change here, like:
//                titleSpace.setText(finalTitle);
//            }
//        };
//        final Handler mHandler = new Handler();
//        mHandler.postDelayed(mUpdateUITimerTask, 10 * 1000);
        String url = "http://www.uvm.edu/~ertait/getGameInfo.php?title="+title;
        JsonArrayRequest gameRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonResponse = null;
                            if (response.length() > 0) {
                                jsonResponse = response.getJSONObject(0);

                            }
                            if (jsonResponse != null){
                                description.setText(jsonResponse.getString("fldGenre"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    };
                },
            new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
                getGame.add(gameRequest);
        final Button threadsbutton = (Button)findViewById(R.id.threadsbutton);
        threadsbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(EmptyGameProfile.this, ThreadList.class);
                startActivity(intent);
            }
        });
    }

}
