package edu.uvm.loginregister;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.BitmapFactory;
import org.w3c.dom.Text;
import android.database.Cursor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class UserAreaActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView IprofilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);
        final TextView subgroups = (TextView) findViewById(R.id.subGroups);
        final TextView subgames = (TextView) findViewById(R.id.subGames);
        final GridLayout subgameslist = (GridLayout) findViewById(R.id.subGamesList);
        final LinearLayout subgroupslist = (LinearLayout) findViewById(R.id.subgroupslist);
        IprofilePic = (ImageView) findViewById(R.id.profilepic);

        IprofilePic.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        //access phone photo library and input a photo

    }

    @Override //this has to get fixed becuase it is a newer model so there needs to be a new permissions file
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
           // IprofilePic.setImageURI(yourSelectedImage);

        }

    }
}