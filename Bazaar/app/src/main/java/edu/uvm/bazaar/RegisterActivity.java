package edu.uvm.bazaar;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


public class RegisterActivity extends AppCompatActivity {
    // private Map<String, String> params;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etFname = (EditText) findViewById(R.id.etFname);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            // THIS HAS TO BE FIXED TO FIT OUR DATABASE. MIGHT ALSO REQUIRE MODIFYING PHP DOCS
            public void onClick(View view) {
                final String name = etFname.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                String url = "http://www.uvm.edu/~ertait/RegisterGet.php?username=" + username;
                JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                // display response
                                try {
                                    JSONObject jsonResponse = null;
                                    if (response.length() > 0) {
                                        jsonResponse = response.getJSONObject(0);
                                    }


                                    if (jsonResponse == null) {

                                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {

                                                if (!response.isEmpty()) {
                                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                    RegisterActivity.this.startActivity(intent);
                                                } else {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                                    builder.setMessage("Register failed").setNegativeButton("Retry", null).create().show();
                                                }

                                            }
                                        };

                                        RegisterRequest registerRequest = new RegisterRequest(name, username, password, responseListener);
                                        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                                        queue.add(registerRequest);
                                    } else {
                                        AlertDialog.Builder inuse = new AlertDialog.Builder(RegisterActivity.this);
                                        inuse.setMessage(" That username is already in use, please try another").setNegativeButton("Retry", null).create().show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                AlertDialog.Builder rspns = new AlertDialog.Builder(RegisterActivity.this);
                                rspns.setMessage(error.toString()).setNegativeButton("Retry", null).create().show();

                            }
                        }
                );

// add it to the RequestQueue
                queue.add(getRequest);

            }
        });
    }
}
