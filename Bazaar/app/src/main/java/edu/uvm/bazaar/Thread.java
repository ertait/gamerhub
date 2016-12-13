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

        ListView listView = (ListView) findViewById(R.id.list);
        TextView threadName = (TextView) findViewById(R.id.Title);
        ArrayList<String> comments = new ArrayList<>();

        Bundle extra = getIntent().getExtras();
        String userId = "";
        String gameId = "";
        String threadTitle = "";
        if (extra != null) {
            gameId = extra.getString("gameId");
            threadTitle = extra.getString("threadTitle");
            userId = extra.getString("userId");
            comments = extra.getStringArrayList("comments");

        }
        threadName.setText(threadTitle);
//        String username1 = "stormwing95: ";
//        String username2="EBear: ";
//        String username3="CBear: ";
//        String username4="IBear: ";


//        values.add(username1+"hey, wat up?");
//        values.add(username2+"not much, you?");
//        values.add(username1+"i love league, but I'm exeptionally salty");
//        values.add(username2+"the only thing weird about that is that you love league");
//        values.add(username3+"yeah, almost everyone hates this game, actually");
//        values.add(username1+"true,true...");
//        values.add(username4+"yeah stormwing95, admit it. u have lol as well");
//        values.add(username1+"fine, fine, i admit it");
//        values.add(username2+"get rekt n00b");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, comments);

        listView.setAdapter(adapter);
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


            @Override
            public void onClick(View view) {

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

                                    Intent intent = new Intent(Thread.this, Thread.class);

                                    intent.putExtra("userId", finalUserId);
                                    intent.putExtra("gameId", finalGameId);
                                    intent.putExtra("comments",finalComments);
                                    intent.putExtra("threadTitle",finalThreadTitle);


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
//                builder.setMessage(newTitle).create().show();
                PostComment thread = new PostComment(finalGameId, finalUserId, finalThreadTitle, threadText, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Thread.this);
                queue.add(thread);
//                final AddThreadRequest thread = null;

//
//// Set up the input
//                final EditText input = new EditText(Thread.this);
//// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
////                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                builder.setView(input);
//
//// Set up the buttons
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        newTitle = input.getText().toString();
//                        PostComment thread = new PostComment(finalGameId, finalUserId, finalThreadTitle, newTitle, responseListener);
//                        RequestQueue queue = Volley.newRequestQueue(Thread.this);
//                        queue.add(thread);
//                    }
//                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//                builder.show();

            }

        });
    }
}