package edu.uvm.bazaar;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import edu.uvm.loginregister.R;

public class LoginActivity extends AppCompatActivity {
    ArrayList<String> subThreads = new ArrayList<>();
    ArrayList<String> subGames = new ArrayList<>();
    ArrayList<String> devGames = new ArrayList<>();
    String userId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword  = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
        bLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                RequestQueue lqueue = Volley.newRequestQueue(LoginActivity.this);
                String url = "http://www.uvm.edu/~ertait/Login.php?username=" + username+"&password"+password;
                JsonArrayRequest loginRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                // display response
                                try {
                                    JSONObject jsonResponse = null;
                                    if (response.length() > 0) {
                                        jsonResponse = response.getJSONObject(0);

                                    }

                                    if (jsonResponse != null && jsonResponse.getString("fldPassword").equals(password)){
                                        if ( jsonResponse.getString("devStatus").equals("A")) {
                                            String url = "http://www.uvm.edu/~ertait/getDevGames.php?userId="+jsonResponse.getString("pmkUserID");
                                            RequestQueue threadQueue = Volley.newRequestQueue(LoginActivity.this);
                                            JsonArrayRequest threadRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                                @Override
                                                public void onResponse(JSONArray response) {

//
//                ArrayList<String> list = new ArrayList<String>();

                                                    try {
                                                        JSONObject jsonResponse = null;
                                                        subThreads.clear();
                                                        subGames.clear();
                                                        if (response.length() > 0) {
                                                            for (int i = 0; i < response.length(); i++) {
                                                                jsonResponse = response.getJSONObject(i);
                                                                String g=(String)jsonResponse.get("fldTitle");
                                                                devGames.add(g);
//                                                                if (jsonResponse.has("txtThreadTitle")){
//                                                                    String u = (String) jsonResponse.getString("txtThreadTitle");
//                                                                    subThreads.add(u);
//                                                                }else if (jsonResponse.has("fldTitle")){
//                                                                    String t = (String) jsonResponse.getString("fldTitle");
//                                                                    subGames.add(t);
//                                                                }

                                                            }
//                                AlertDialog.Builder rspns = new AlertDialog.Builder(LoginActivity.this);
//                                rspns.setMessage(response.toString()).setNegativeButton("Retry", null).create().show();

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
                                                        Intent registerIntent = new Intent(LoginActivity.this, DeveloperAreaActivity.class);
//                                            userId = jsonResponse.getString("pmkUserID");
                                            registerIntent.putExtra("UserId",userId);
                                            registerIntent.putExtra("Username",username);
                                            registerIntent.putExtra("devGames",devGames);
//                                            registerIntent.putExtra("devStatus",jsonResponse.getString("devStatus"));
                                            LoginActivity.this.startActivity(registerIntent);
                                                        //THIS NEEDS TO GET THE SUB THREADS AND THEN PASS THEM TO THE THREAD CLASS TO DISPLAY
//                                                        Intent registerIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
//                                                        registerIntent.putExtra("UserId",userId);
//                                                        registerIntent.putExtra("Username",username);
//                                                        registerIntent.putExtra("subThreads",subThreads);
//                                                        registerIntent.putExtra("subGames",subGames);
//                                                        LoginActivity.this.startActivity(registerIntent);


                                                        ;
//                                                        startActivity(registerIntent);
//                    AlertDialog.Builder rspns = new AlertDialog.Builder(LoginActivity.this);
//                    rspns.setMessage(subGames.toString()+subThreads.toString()+userId).setNegativeButton("Retry", null).create().show();
//                                                        if (jsonResponse != null){
////                        JSONArray array = (JSONArray)jsonResponse;
////                                for (int i=0;i<response.length();i++){
//////                                    values.add(response.getString(i));
//
////
//                                                        }

                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    AlertDialog.Builder rspns = new AlertDialog.Builder(LoginActivity.this);
                                                    rspns.setMessage(error.toString()).setNegativeButton("Retry", null).create().show();
                                                }
                                            });
                                            threadQueue.add(threadRequest);
//
                                        }
//                                        else if (jsonResponse.getString("devStatus").equals("U")){
//                                            Intent registerIntent = new Intent(LoginActivity.this, DevUser.class);
//                                            LoginActivity.this.startActivity(registerIntent);
//                                        }
                                        else{
                                            userId = jsonResponse.getString("pmkUserID");
                                            String url = "http://www.uvm.edu/~ertait/getUserThreads.php?userId="+jsonResponse.getString("pmkUserID");
                                            RequestQueue threadQueue = Volley.newRequestQueue(LoginActivity.this);
                                            JsonArrayRequest threadRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                                @Override
                                                public void onResponse(JSONArray response) {

//
//                ArrayList<String> list = new ArrayList<String>();

                                                    try {
                                                        JSONObject jsonResponse = null;
                                                        subThreads.clear();
                                                        subGames.clear();
                                                        if (response.length() > 0) {
                                                            for (int i = 0; i < response.length(); i++) {
                                                                jsonResponse = response.getJSONObject(i);
                                                                if (jsonResponse.has("txtThreadTitle")){
                                                                    String u = (String) jsonResponse.getString("txtThreadTitle");
                                                                    subThreads.add(u);
                                                                }else if (jsonResponse.has("fldTitle")){
                                                                    String t = (String) jsonResponse.getString("fldTitle");
                                                                    subGames.add(t);
                                                                }

                                                            }
//                                AlertDialog.Builder rspns = new AlertDialog.Builder(LoginActivity.this);
//                                rspns.setMessage(response.toString()).setNegativeButton("Retry", null).create().show();

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
                                                        //THIS NEEDS TO GET THE SUB THREADS AND THEN PASS THEM TO THE THREAD CLASS TO DISPLAY
                                                        Intent registerIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                                        registerIntent.putExtra("UserId",userId);
                                                        registerIntent.putExtra("Username",username);
                                                        registerIntent.putExtra("subThreads",subThreads);
                                                        registerIntent.putExtra("subGames",subGames);
                                                        LoginActivity.this.startActivity(registerIntent);


                                                        ;
//                                                        startActivity(registerIntent);
//                    AlertDialog.Builder rspns = new AlertDialog.Builder(LoginActivity.this);
//                    rspns.setMessage(subGames.toString()+subThreads.toString()+userId).setNegativeButton("Retry", null).create().show();
//                                                        if (jsonResponse != null){
////                        JSONArray array = (JSONArray)jsonResponse;
////                                for (int i=0;i<response.length();i++){
//////                                    values.add(response.getString(i));
//
////
//                                                        }

                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    AlertDialog.Builder rspns = new AlertDialog.Builder(LoginActivity.this);
                                                    rspns.setMessage(error.toString()).setNegativeButton("Retry", null).create().show();
                                                }
                                            });
                                            threadQueue.add(threadRequest);
//
                                        }
                                    }
                                    else if (jsonResponse == null){
                                        AlertDialog.Builder rspns = new AlertDialog.Builder(LoginActivity.this);
                                        rspns.setMessage("That username entered incorrectly or not in use. Please retry or register").setNegativeButton("Retry", null).create().show();
                                    }
                                    else{
                                        AlertDialog.Builder rspns = new AlertDialog.Builder(LoginActivity.this);
                                        rspns.setMessage("Username and password do not match, please try again").setNegativeButton("Retry", null).create().show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                AlertDialog.Builder rspns = new AlertDialog.Builder(LoginActivity.this);
                                rspns.setMessage(error.toString()).setNegativeButton("Retry", null).create().show();

                            }
                        }
                );


                lqueue.add(loginRequest);

//            }
            }
        });
    }
}
