package wdsc.ggateway.net.ggatewayrecyclerview01;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Student s1 = new Student();
        s1.setName("Rami");
        s1.setPhoto(R.drawable.p1);

        Student s2 = new Student();
        s2.setName("Ahmed");
        s2.setPhoto(R.drawable.p2);

        Student s3 = new Student();
        s3.setName("Ali");
        s3.setPhoto(R.drawable.p3);

        Student s4 = new Student();
        s4.setName("Omar");
        s4.setPhoto(R.drawable.p4);

        final List<Student> students = new ArrayList<Student>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        final MyRecyclerViewAdapter adapter =new MyRecyclerViewAdapter(getApplicationContext(),students,R.layout.rv_layout);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        //GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2);
        rv.setLayoutManager(manager);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                students.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
            }
        });
        helper.attachToRecyclerView(rv);

        DividerItemDecoration d = new DividerItemDecoration(getApplicationContext(), manager.getOrientation());
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.divider_shape);
        d.setDrawable(drawable);
        rv.addItemDecoration(d);
    }
}
