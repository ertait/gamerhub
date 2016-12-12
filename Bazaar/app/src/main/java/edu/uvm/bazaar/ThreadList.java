package edu.uvm.bazaar;

        import android.content.Intent;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;

        import edu.uvm.loginregister.R;

public class ThreadList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_list);

        ListView listView =(ListView) findViewById(R.id.list);

        String[] values = new String[]{"Thread1","Thread2","Thread3","Etc."};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,values);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //Toast.makeText(main.this, "" + position,
                //Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ThreadList.this, Thread.class);
                startActivity(intent);

            }
        });
        final Button add = (Button) findViewById(R.id.addThread);
        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                AlertDialog.Builder newThread = new AlertDialog.Builder(ThreadList.this);
                newThread.setMessage("Add a new thread for Emma's absoulte pleasure").create().show();
            }
        });


    }
}

