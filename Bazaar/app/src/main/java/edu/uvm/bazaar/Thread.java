package edu.uvm.bazaar;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;

import edu.uvm.loginregister.R;

public class Thread extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        final ListView listView = (ListView) findViewById(R.id.list);
        TextView threadName = (TextView) findViewById(R.id.Title);
        Button sub = (Button) findViewById(R.id.subscribe);
        ArrayList<String> comments = new ArrayList<>();

        Bundle extra = getIntent().getExtras();
        String userId = "";
        String gameId = "none";
        String threadTitle = "";
        if (extra != null) {
//            if (!extra.getString("gameId").equals("")) {
                gameId = extra.getString("gameId");
//            }
            threadTitle = extra.getString("threadTitle");
            userId = extra.getString("userId");
            comments = extra.getStringArrayList("comments");

        }
        threadName.setText(threadTitle);
        if (gameId==null){
            gameId="none";
        }



        Button post = (Button) findViewById(R.id.btn);
        final String finalThreadTitle = threadTitle;
        final ArrayList<String> finalComments = comments;
        final String finalGameId = gameId;
        final String finalUserId = userId;
        final ArrayList<String> finalComments1 = comments;
        final String finalUserId1 = userId;
        final String finalGameId1 = gameId;
        final String finalThreadTitle1 = threadTitle;
        final String finalThreadTitle2 = threadTitle;
        post.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder rspns = new AlertDialog.Builder(Thread.this);
//                rspns.setMessage(finalUserId).setNegativeButton("Retry", null).create().show();

                final Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        String url = "http://www.uvm.edu/~ertait/gameforum.php?threadTitle="+finalThreadTitle;
                        RequestQueue threadQueue = Volley.newRequestQueue(Thread.this);
                        JsonArrayRequest threadRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

//
//                ArrayList<String> list = new ArrayList<String>();

                                try {
                                    JSONObject jsonResponse = null;
                                    finalComments.clear();
                                    if (response.length() > 0) {
                                        for (int i = 0; i < response.length(); i++) {
                                            jsonResponse = response.getJSONObject(i);
                                            String u = (String) jsonResponse.getString("fldUsername");
                                            String r = (String) jsonResponse.getString("txtThread");
                                            String c = u+": "+r;
                                            finalComments.add(c);
                                        }
//                                        AlertDialog.Builder rspns = new AlertDialog.Builder(Thread.this);
//                                        rspns.setMessage(finalUserId).setNegativeButton("Retry", null).create().show();

                                    }

                                    Intent intent = new Intent(Thread.this, Thread.class);

                                    intent.putExtra("userId", finalUserId);
                                    intent.putExtra("gameId", finalGameId);
                                    intent.putExtra("comments",finalComments);
                                    intent.putExtra("threadTitle",finalThreadTitle);


                                    ;
//                                    AlertDialog.Builder rspns = new AlertDialog.Builder(Thread.this);
//                               rspns.setMessage(finalUserId).setNegativeButton("Retry", null).create().show();
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
                                AlertDialog.Builder rspns = new AlertDialog.Builder(Thread.this);
                                rspns.setMessage(error.toString()).setNegativeButton("Retry", null).create().show();
                            }
                        });
                        threadQueue.add(threadRequest);
                    }
                };
                EditText postText = (EditText) findViewById(R.id.editText);
                String threadText = postText.getText().toString();
//                AlertDialog.Builder builder = new AlertDialog.Builder(Thread.this);
//                builder.setMessage("userid:"+finalUserId+" ").create().show();
                PostComment thread = new PostComment(finalGameId, finalUserId, finalThreadTitle, threadText, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Thread.this);
                queue.add(thread);

                String subject ="New Post";
                String body = "A thread you subscribe to has been updated!";
                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification.Builder
                        (getApplicationContext()).setContentText(body).
                        setContentTitle(subject).setSmallIcon(R.drawable.star_icon).build();


                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0, notify);

            }

        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, comments);
        final TextView tName = threadName;
        final String tTitle = threadTitle;
        final Button sub2 = sub;
        listView.setAdapter(adapter);
        sub.setOnClickListener(new View.OnClickListener() {
//            tName.setText(tTitle);
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                final Response.Listener<String> listener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        if (!response.isEmpty()) {
                            AlertDialog.Builder rspns = new AlertDialog.Builder(Thread.this);
                            rspns.setMessage("You have subscribed to the thread: "+finalThreadTitle).setNegativeButton("Ok", null).create().show();
                        }
                    }
                };
                subscribeRequest subRequest = new subscribeRequest(finalUserId,finalThreadTitle,listener);
                RequestQueue subscribe = Volley.newRequestQueue(Thread.this);
//                String url = "http://www.uvm.edu/~ertait/subscribethread.php?userId="+finalUserId+"&threadName="+finalThreadTitle;
//                JsonArrayRequest gameRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                try {
//                                    JSONObject jsonResponse = null;
//                                    if (response.length() > 0) {
//                                        jsonResponse = response.getJSONObject(0);
//
//                                    }
//                                    if (jsonResponse != null){
////                                        description.setText(jsonResponse.getString("fldGenre"));
////                                        GAMEID = jsonResponse.getString("pmkGameId");
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                            };
//                        },
//                        new Response.ErrorListener() {
//
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//
//                            }
//                        });

                subscribe.add(subRequest);


            }
        });

    }


}