package dummy.popdesign.cllg_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class signup_login extends AppCompatActivity {

    FloatingActionButton fab;
    CardView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);

        fab = (FloatingActionButton) findViewById(R.id.add_user);
        cv = (CardView) findViewById(R.id.already_user);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup_login.this, login.class));
            }
        });
    }
}
