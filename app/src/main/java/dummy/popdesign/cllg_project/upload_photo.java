
package dummy.popdesign.cllg_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;

public class upload_photo extends AppCompatActivity {

ImageView im;
    EditText desc;
    FloatingActionButton upload;
   Uri uri;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser currentUser;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase_user;


    private String user_id;


 private static final int GALLERY_REQUEST=1;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);
        im = (ImageView) findViewById(R.id.image_upload);
        desc= (EditText) findViewById(R.id.description);
        upload= (FloatingActionButton) findViewById(R.id.Upload_database);
//firebase
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        username=currentUser.getEmail();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Blog");
        mDatabase_user=FirebaseDatabase.getInstance().getReference().child("Users");

//firebase end
         im.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
                 galleryIntent.setType("image/*");
                 startActivityForResult(galleryIntent,GALLERY_REQUEST);
             }
         });

        mDatabase_user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                  if(dataSnapshot.hasChild(currentUser.getUid()))
                  {
                   user_id= (String) dataSnapshot.child(currentUser.getUid()).child("name").getValue();
                  }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPosting();



            }
        });


         }

    private void startPosting() {

        final String description=desc.getText().toString().trim();

        if(!TextUtils.isEmpty(description)&&!TextUtils.isEmpty(username) &&uri!=null)
        {


            //Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
            StorageReference riversRef = mStorageRef.child(uri.getLastPathSegment());

            riversRef.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            @SuppressWarnings("VisibleForTests")   Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            DatabaseReference newPost=mDatabase.push();
                            newPost.child("uid").setValue(user_id);

                            newPost.child("description").setValue(description);
                            newPost.child("photo").setValue(downloadUrl.toString());
                            Toast.makeText(upload_photo.this, "Successful", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(upload_photo.this,MainActivity.class));
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                        }
                    });


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK)
        {uri=data.getData();
         /* im.setImageURI(uri);*/
            Picasso.with(getApplicationContext())
                    .load(uri)

                    .into(im);
    }}
}

