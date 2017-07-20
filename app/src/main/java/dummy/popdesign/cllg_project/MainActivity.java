package dummy.popdesign.cllg_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

private RecyclerView recyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv;
       MainAdapter mainAdapter=new MainAdapter(getApplicationContext());
        recyclerView= (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mainAdapter);
        ArrayList<Info_for_Main> list=new ArrayList<>();
        list.add(new Info_for_Main("akash","7 days","descjhedjkjk","100"));
        list.add(new Info_for_Main("sdash","7 days","descjsdhedjkjk","100"));

        list.add(new Info_for_Main("aksdsash","7 days","descjhedjkjk","200"));

        list.add(new Info_for_Main("asdskash","7 days","descjhedjkjk","100"));

        mainAdapter.setData(list);


        final View contextView = findViewById(R.id.photo);

       /*iv = (ImageView) findViewById(R.id.photo);

        *//** for setting title in the middle**//*
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(getLayoutInflater().inflate(R.layout.action_bar_home, null),
                new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.MATCH_PARENT,
                        Gravity.CENTER
                )
        );

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(contextView, R.string.home, Snackbar.LENGTH_LONG).show();

                startActivity(new Intent(MainActivity.this,signup_login.class));
            }
        });*/


    }
    /** for search icon on action bar**/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.search) {
            startActivity(new Intent(this, signup_login.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
