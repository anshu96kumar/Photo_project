package dummy.popdesign.cllg_project;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class MainFragment extends Fragment {

    private static final String TAG = "Mainactivity";
    private RecyclerView recyclerView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String username;
    //firebase
    FirebaseUser currentUser;
    DatabaseReference mDatabase;

    FloatingActionButton fab_adding_pic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.activity_main, null, false);










        //getting current user email
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        username = currentUser.getEmail();

        ///////firebase-------
        ImageView iv;


        // MainAdapter mainAdapter=new MainAdapter(getApplicationContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        //recyclerView.setAdapter(mainAdapter);
       /* ArrayList<Info_for_Main> list=new ArrayList<>();
        list.add(new Info_for_Main("akash","7 days","descjhedjkjk","100"));
        list.add(new Info_for_Main("sdash","7 days","descjsdhedjkjk","100"));

        list.add(new Info_for_Main("aksdsash","7 days","descjhedjkjk","200"));

        list.add(new Info_for_Main("asdskash","7 days","descjhedjkjk","100"));
        mainAdapter.setData(list);*/


        //firebase

        fab_adding_pic = (FloatingActionButton) view.findViewById(R.id.fab_add_photo);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && fab_adding_pic.isShown())
                    fab_adding_pic.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState ==  RecyclerView.SCROLL_STATE_IDLE) {
                    fab_adding_pic.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });


        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    username = user.getEmail().toString();
                    Toast.makeText(getActivity(), user.getEmail(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // User is signed out
                    Toast.makeText(getActivity(), "Not logged in", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...


            }
        };


        final View contextView = view.findViewById(R.id.photo);

        iv = (ImageView) view.findViewById(R.id.photo);

        fab_adding_pic = (FloatingActionButton) view.findViewById(R.id.fab_add_photo);


        fab_adding_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), upload_photo.class));
            }
        });

       /* ActionBar actionBar = getSupportActionBar();
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

*/
       /* iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(contextView, R.string.home, Snackbar.LENGTH_LONG).show();

                startActivity(new Intent(MainActivity.this,signup_login.class));
            }
        });
*/


        currentUser = mAuth.getCurrentUser();
        mAuth.addAuthStateListener(mAuthListener);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
        //updateUI(currentUser);

        FirebaseRecyclerAdapter<Info_for_Main, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Info_for_Main, BlogViewHolder>(


                Info_for_Main.class,
                R.layout.recyclerview,
                BlogViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Info_for_Main model, int position) {

                viewHolder.setUid(model.getUid());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setPhoto(getActivity().getApplicationContext(), model.getPhoto());

            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);


        return view;
    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDescription(String desc) {
            TextView description = (TextView) mView.findViewById(R.id.description);
            description.setText(desc);
        }

        public void setUid(String userid) {
            TextView uid = (TextView) mView.findViewById(R.id.name);
            uid.setText(userid);

        }

        public void setPhoto(Context ctx, String image) {
            ImageView imageView = (ImageView) mView.findViewById(R.id.photo);

            Picasso.with(ctx).load(image).into(imageView);

        }


    }


}
