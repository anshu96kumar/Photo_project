package dummy.popdesign.cllg_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                startActivity(new Intent(splash_screen.this, MainActivity.class));

            }
        }, 4000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
