package gjj.listviewanimindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingRightInAnimationAdapter;
import com.nhaarman.prepared.SwingBottomInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.listView)
    ListView listView;

    private List<String> list = new ArrayList<>();
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        adapter=new MyAdapter();
//        AnimationAdapter animAdapter = new SwingBottomInAnimationAdapter(adapter);//从底部向上慢慢的展示效果
//        AnimationAdapter animAdapter = new SwingLeftInAnimationAdapter(adapter);
//        AnimationAdapter animAdapter = new SwingRightInAnimationAdapter(adapter);
//        AnimationAdapter animAdapter = new AlphaInAnimationAdapter(adapter);
        AnimationAdapter animAdapter = new ScaleInAnimationAdapter(adapter);

        animAdapter.setAnimationDurationMillis(300);
        animAdapter.setAbsListView(listView);
        listView.setAdapter(animAdapter);
    }

    public void initData() {
        for (int i = 0; i < 100; i++) {
            list.add("这是第" + i + "个条目");
        }
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView==null){
                convertView=View.inflate(MainActivity.this, R.layout.content_main, null);
                viewHolder=new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.tvDemo.setText(list.get(position));
            return convertView;
        }


    }
    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'content_main.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    class ViewHolder {
        @Bind(R.id.tv_demo)
        TextView tvDemo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
