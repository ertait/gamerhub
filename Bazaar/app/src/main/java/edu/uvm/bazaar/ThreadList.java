package edu.uvm.bazaar;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.InputType;
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

        import java.lang.*;
        import java.util.ArrayList;

        import edu.uvm.loginregister.R;

public class ThreadList extends AppCompatActivity {
    ArrayList<String> comments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_list);

        ListView listView =(ListView) findViewById(R.id.list);
        final TextView titleText = (TextView) findViewById(R.id.name);
        String gameId="";
//        String username="";
        String title="";
        String userId="";
        ArrayList<String> values = new ArrayList<>();
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            gameId = extra.getString("gameId");
//            username = extra.getString("username");
            values = extra.getStringArrayList("values");
            title = extra.getString("title");
            userId = extra.getString("userId");

        }


        ;
            titleText.setText(title);
//        String[] values1 = new String[]{"Thread1","Thread2","Thread3","Etc."};
//        String[] values1 = new String[values.size()];
//        values.toArray(values1);
//        AlertDialog.Builder rspns = new AlertDialog.Builder(ThreadList.this);
//        rspns.setMessage("values:"+values+" values1:"+values1).setNegativeButton("Retry", null).create().show();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,values);

        listView.setAdapter(adapter);
        final String finalGameId = gameId;
        final String finalGameId5 = gameId;
        final String finalUserId2 = userId;
        final String finalGameId6 = gameId;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, View v,
                                    final int position, long id) {
                final String threadTitle = (String)parent.getItemAtPosition(position);
                //Toast.makeText(main.this, "" + position,
                //Toast.LENGTH_SHORT).show();
                String url = "http://www.uvm.edu/~ertait/gameforum.php?threadTitle="+threadTitle;
                RequestQueue threadQueue = Volley.newRequestQueue(ThreadList.this);
                JsonArrayRequest threadRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

//
//                ArrayList<String> list = new ArrayList<String>();

                        try {
                            JSONObject jsonResponse = null;
                            comments.clear();
                            if (response.length() > 0) {
                                for (int i = 0; i < response.length(); i++) {
                                    jsonResponse = response.getJSONObject(i);
                                    String u = (String) jsonResponse.getString("fldUsername");
                                    String r = (String) jsonResponse.getString("txtThread");
                                    String c = u+": "+r;
                                    comments.add(c);
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
                            Intent intent = new Intent(ThreadList.this, Thread.class);

                            intent.putExtra("userId", finalUserId2);
                            intent.putExtra("gameId", finalGameId5);
                            intent.putExtra("comments",comments);
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
                        AlertDialog.Builder rspns = new AlertDialog.Builder(ThreadList.this);
                        rspns.setMessage(error.toString()).setNegativeButton("Retry", null).create().show();
                    }
                });
                threadQueue.add(threadRequest);


            }
        });
        final Button add = (Button) findViewById(R.id.addThread);

        final String finalGameId1 = gameId;
//        final String finalUsername = username;
        final String finalGameId2 = gameId;
        final String finalGameId3 = gameId;
        final ArrayList<String> finalValues = values;
        final String finalTitle = title;
        final String finalGameId4 = gameId;

        final String finalUserId = userId;
        final String finalUserId1 = userId;
        add.setOnClickListener(new View.OnClickListener(){
            String newTitle ="";
            @Override
            public void onClick(View view) {

                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (!response.isEmpty()) {
//                            AlertDialog.Builder rspns = new AlertDialog.Builder(ThreadList.this);
//                            rspns.setMessage("not empty").setNegativeButton("Retry", null).create().show();
                            String url = "http://www.uvm.edu/~ertait/getGameThreads.php?gameId="+ finalGameId4;
                            RequestQueue threadQueue = Volley.newRequestQueue(ThreadList.this);
                            JsonArrayRequest threadRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {

//
//                ArrayList<String> list = new ArrayList<String>();

                                    try {
                                        JSONObject jsonResponse = null;
                                        finalValues.clear();
                                        if (response.length() > 0) {
                        for (int i = 0; i < response.length(); i++) {
                            jsonResponse = response.getJSONObject(i);
                            String r = (String) jsonResponse.getString("txtThreadName");

                            finalValues.add(r);
                        }
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
//                    AlertDialog.Builder rspns = new AlertDialog.Builder(EmptyGameProfile.this);
//                    rspns.setMessage(response.toString()+jsonResponse.getString("txtThreadName").getClass().getName()+"values:"+values).setNegativeButton("Retry", null).create().show();
                                        if (jsonResponse != null){
//                        JSONArray array = (JSONArray)jsonResponse;
//                                for (int i=0;i<response.length();i++){
////                                    values.add(response.getString(i));

                                            Intent intent = new Intent(ThreadList.this, ThreadList.class);
                                            intent.putExtra("gameId", finalGameId3);
//                                            intent.putExtra("username", finalUsername);
                                            intent.putExtra("values", finalValues);
                                            intent.putExtra("title", finalTitle);
                                            intent.putExtra("userId", finalUserId1);
//                                AlertDialog.Builder rspns = new AlertDialog.Builder(EmptyGameProfile.this);
//                                rspns.setMessage(values.toString()).setNegativeButton("Retry", null).create().show();
                                            startActivity(intent);
//
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    AlertDialog.Builder rspns = new AlertDialog.Builder(ThreadList.this);
                                    rspns.setMessage(error.toString()).setNegativeButton("Retry", null).create().show();
                                }
                            });
                            threadQueue.add(threadRequest);
                            Intent intent = new Intent(ThreadList.this, ThreadList.class);
                            ThreadList.this.startActivity(intent);
                        }
//                        else {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(ThreadList.this);
//                            builder.setMessage("Register failed").setNegativeButton("Retry", null).create().show();
//                        }

                    }
                };
//                final AddThreadRequest thread = null;
                AlertDialog.Builder builder = new AlertDialog.Builder(ThreadList.this);
                builder.setTitle("Enter the title of the new thread");

// Set up the input
                final EditText input = new EditText(ThreadList.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newTitle = input.getText().toString();
                        AddThreadRequest thread = new AddThreadRequest(finalGameId2, finalUserId, newTitle, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(ThreadList.this);
                        queue.add(thread);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();



//                titleText.setText(newTitle);
//                AlertDialog.Builder newThread = new AlertDialog.Builder(ThreadList.this);
//                newThread.setMessage("Add a new thread for Emma's absoulte pleasure").create().show();
            }
        });


    }

}

