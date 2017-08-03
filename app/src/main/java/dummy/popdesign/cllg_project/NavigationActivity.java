package dummy.popdesign.cllg_project;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





        ///////////////////////////////////////////////////////////

        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FT.replace(R.id.parent, new MainFragment());
        FT.commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home_nav) {

            FragmentManager FM = getFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();
            FT.replace(R.id.parent, new MainFragment());
            FT.commit();



        } else if (id == R.id.my_account_nav) {

            FragmentManager FM = getFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();
            FT.replace(R.id.parent, new Profile());
            FT.commit();



        } else if (id == R.id.fav_nav) {

        } else if (id == R.id.sign_out_nav) {
            mAuth=FirebaseAuth.getInstance();
            mAuth.signOut();
            startActivity(new Intent(this, signup_login.class));


        } else if (id == R.id.faq_nav) {

        } else if (id == R.id.about_nav) {

            FragmentManager FM = getFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();
            FT.replace(R.id.parent, new AboutFragment());
            FT.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
