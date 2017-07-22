package dummy.popdesign.cllg_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG ="Mainactivity" ;
    private RecyclerView recyclerView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String username;
    //firebase
    FirebaseUser currentUser;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        mAuth.addAuthStateListener(mAuthListener);
        //updateUI(currentUser);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//getting current user email
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

  username=currentUser.getEmail();

        ///////firebase-------
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



//firebase

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                     username = user.getEmail().toString();
                    Toast.makeText(MainActivity.this, user.getEmail(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // User is signed out
                    Toast.makeText(MainActivity.this, "Not logged in", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };










        final View contextView = findViewById(R.id.photo);

       iv = (ImageView) findViewById(R.id.photo);

        //** for setting title in the middle**//*
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.action_bar_home, null);
        actionBar.setCustomView(v,
                new ActionBar.LayoutParams(

                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.MATCH_PARENT,
                        Gravity.CENTER
                )
        );
        TextView tv=v.findViewById(R.id.home_title);
        tv.setText(username);

       /* iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(contextView, R.string.home, Snackbar.LENGTH_LONG).show();

                startActivity(new Intent(MainActivity.this,signup_login.class));
            }
        });
*/

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
