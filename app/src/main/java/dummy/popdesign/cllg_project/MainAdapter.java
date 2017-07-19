package dummy.popdesign.cllg_project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ABHISHEK on 19-07-2017.
 */

public class MainAdapter extends Adapter<MainAdapter.MyViewHolder> {

   private LayoutInflater inflator;
    private ArrayList<Info_for_Main> data=new ArrayList<>();


    public ArrayList<Info_for_Main> getData() {
        return data;
    }

    public void setData(ArrayList<Info_for_Main> data) {
        this.data = data;

    }

    public MainAdapter(Context context) {

        this.inflator=LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflator.inflate(R.layout.recyclerview,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;



    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       Info_for_Main current =data.get(position);

        holder.name.setText(current.getName());
        holder.description.setText(current.getDescription());
        holder.date.setText(current.getDate());
        holder.likes.setText(current.getLikes());



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
    TextView name,date,description,likes;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            description=itemView.findViewById(R.id.description);
           date=itemView.findViewById(R.id.time);
            likes=itemView.findViewById(R.id.count_fav);

        }
    }
}
