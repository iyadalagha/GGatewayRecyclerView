package wdsc.ggateway.net.ggatewayrecyclerview01;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by PROBOOK on 8/20/2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>{
    Context context;
    List<Student> students;
    int layout;

    public MyRecyclerViewAdapter(Context context, List<Student> students, int layout){
        this.context = context;
        this.students = students;
        this.layout = layout;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout,parent, false);
        final MyViewHolder vh = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = vh.getAdapterPosition();
                String name = students.get(position).getName();
                Toast.makeText(context,name,Toast.LENGTH_LONG).show();
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Student s = students.get(position);
        holder.im.setImageResource(s.getPhoto());
        holder.tv.setText(s.getName());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView im;
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            im = (ImageView)itemView.findViewById(R.id.im);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
