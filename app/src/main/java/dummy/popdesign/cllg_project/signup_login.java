package dummy.popdesign.cllg_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signup_login extends AppCompatActivity {

    FloatingActionButton fab, add_user;
    CardView cv, upload_photo;
    EditText user_name, pass, conf_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);


        final View context_name = findViewById(R.id.name);
        final View context_pass = findViewById(R.id.fpass);
        final View context_cpass = findViewById(R.id.cpass);
        final View context_upload_photo = findViewById(R.id.upload_photo);



        fab = (FloatingActionButton) findViewById(R.id.add_user);
        cv = (CardView) findViewById(R.id.already_user);

        user_name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.fpass);
        conf_pass = (EditText) findViewById(R.id.cpass);
        upload_photo = (CardView) findViewById(R.id.upload_photo);

        add_user = (FloatingActionButton) findViewById(R.id.add_user);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup_login.this, login.class));
            }
        });

        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = user_name.getText().toString();
                String password = pass.getText().toString();
                String confirm_pass = conf_pass.getText().toString();

                boolean flag = true;

                if (name.equals("")) {
                    Snackbar.make(context_name, "This Field can't be empty", Snackbar.LENGTH_LONG).show();
                    flag = false;
                }

                if (password.equals("")) {
                    Snackbar.make(context_pass, "This Field can't be empty", Snackbar.LENGTH_LONG).show();
                    flag = false;
                }

                if (confirm_pass.equals("")) {
                    Snackbar.make(context_cpass, "This Field can't be empty", Snackbar.LENGTH_LONG).show();
                    flag = false;
                }


                String firstpass = pass.getText().toString(),
                        secondpass = conf_pass.getText().toString();


                if (firstpass.indexOf('@') == -1) {
                    Snackbar.make(context_cpass, "Password should have a special character", Snackbar.LENGTH_LONG).show();
                }

                if (firstpass.equals(secondpass)) {

                    flag = true;
                } else {
                    Snackbar.make(context_cpass, "Password does't match", Snackbar.LENGTH_LONG).show();
                }

                if (flag) {
                    Toast.makeText(signup_login.this, "User is Registered", Toast.LENGTH_LONG).show();
                }
            }


        });
    }
}
