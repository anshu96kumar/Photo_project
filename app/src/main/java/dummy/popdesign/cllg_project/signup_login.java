package dummy.popdesign.cllg_project;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup_login extends BaseActivity implements  View.OnClickListener{

    FloatingActionButton fab, add_user;
    CardView  upload_photo;
    EditText email, pass, conf_pass;
    public final String TAG =signup_login.class.getName() ;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
   //firebase


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuth.addAuthStateListener(mAuthListener);
        //updateUI(currentUser);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);


        final View context_name = findViewById(R.id.name);
        final View context_pass = findViewById(R.id.fpass);
        final View context_cpass = findViewById(R.id.cpass);
        final View context_upload_photo = findViewById(R.id.upload_photo);



        fab = (FloatingActionButton) findViewById(R.id.add_user);
          fab.setOnClickListener(this);

        email = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.fpass);
        conf_pass = (EditText) findViewById(R.id.cpass);
        upload_photo = (CardView) findViewById(R.id.upload_photo);

        add_user = (FloatingActionButton) findViewById(R.id.add_user);




        //firebase
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        ///firebase












    }



    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(signup_login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }



    private boolean validateForm() {
        boolean valid = true;

        String emailstr = email.getText().toString();
        if (TextUtils.isEmpty(emailstr)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }

        String passwordstr = pass.getText().toString();
        if (TextUtils.isEmpty(passwordstr)) {
            pass.setError("Required.");
            valid = false;
        } else {
            pass.setError(null);
        }

        return valid;
    }


    //click lsiteners
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.add_user) {
            createAccount(email.getText().toString(), pass.getText().toString());
        }


    }


}
