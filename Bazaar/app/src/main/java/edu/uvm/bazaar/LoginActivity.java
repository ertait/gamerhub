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

import edu.uvm.loginregister.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
        bLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                RequestQueue lqueue = Volley.newRequestQueue(LoginActivity.this);
                String url = "http://www.uvm.edu/~ertait/Login.php?username=" + username + "&password" + password;
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

                                    if (jsonResponse != null && jsonResponse.getString("fldPassword").equals(password)) {
                                        Intent registerIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                        LoginActivity.this.startActivity(registerIntent);
                                    } else if (jsonResponse == null) {
                                        AlertDialog.Builder rspns = new AlertDialog.Builder(LoginActivity.this);
                                        rspns.setMessage("That username entered incorrectly or not in use. Please retry or register").setNegativeButton("Retry", null).create().show();
                                    } else {
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