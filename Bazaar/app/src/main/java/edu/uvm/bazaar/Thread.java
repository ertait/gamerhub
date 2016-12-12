package edu.uvm.bazaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import edu.uvm.loginregister.R;

public class Thread extends AppCompatActivity {
    ArrayList<String> values = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        ListView listView =(ListView) findViewById(R.id.list);

        String username1 = "stormwing95: ";
        String username2="EBear: ";
        String username3="CBear: ";
        String username4="IBear: ";


        values.add(username1+"hey, wat up?");
        values.add(username2+"not much, you?");
        values.add(username1+"i love league, but I'm exeptionally salty");
        values.add(username2+"the only thing weird about that is that you love league");
        values.add(username3+"yeah, almost everyone hates this game, actually");
        values.add(username1+"true,true...");
        values.add(username4+"yeah stormwing95, admit it. u have lol as well");
        values.add(username1+"fine, fine, i admit it");
        values.add(username2+"get rekt n00b");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,values);

        listView.setAdapter(adapter);



    }
    public void addPost (View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        values.add(message);
        Intent intent = new Intent(this, ThreadList.class);

        startActivity(intent);
    }
}
