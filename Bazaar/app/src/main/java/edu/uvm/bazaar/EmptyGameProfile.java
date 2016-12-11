package edu.uvm.bazaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.uvm.loginregister.R;

public class EmptyGameProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_game_profile);

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
