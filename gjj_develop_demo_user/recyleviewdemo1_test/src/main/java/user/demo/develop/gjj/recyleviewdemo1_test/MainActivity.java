package user.demo.develop.gjj.recyleviewdemo1_test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.list)
    RecyclerView list;
    private MyAdapter adapter;
    private List<TestBean> mlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 100; i++) {
            TestBean bean = new TestBean();
            bean.content = "这是第" + i + "个条目";
            mlist.add(bean);
        }

        //通过加载XML动画设置文件来创建一个Animation对象；
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_right);
        //得到一个LayoutAnimationController对象；
        LayoutAnimationController lac = new LayoutAnimationController(animation);
//        LayoutAnimationController lac =AnimationUtils.loadLayoutAnimation(this, R.anim.slide_right);
        //设置控件显示的顺序；normal	0	   默认
//                            reverse	1	  倒序
//                            random	2	  随机
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //设置控件显示间隔时间；
        lac.setDelay((float) 0.2);
        //为ListView设置LayoutAnimationController属性；
        list.setLayoutAnimation(lac);
//        list.setAdapter(adapter = new TestAdapter(MainActivity.this,mlist));
        adapter=new MyAdapter(mlist,MainActivity.this);
        list.setAdapter(adapter);
        //设置点击事件
       adapter.setOnItemClickLitener(new BaseAdapter.OnItemClickLitener() {
           @Override
           public void onItemClick(View view, int position) {
               Toast.makeText(MainActivity.this,position+"点击了",Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onItemLongClick(View view, int position) {
               Toast.makeText(MainActivity.this,position+"长按了",Toast.LENGTH_SHORT).show();
           }
       });
    }

    public class MyAdapter extends BaseAdapter<TestBean> {
        List<TestBean> mItemDataList;
        Activity mContext;
        public MyAdapter(List<TestBean> mItemDataList, Activity mContext) {
            super(mItemDataList, mContext);
            this.mItemDataList=mItemDataList;
            this.mContext=mContext;
        }

        @Override
        public void showData(BaseViewHolder holder, int i, List<TestBean> mItemDataList) {
            ViewHolder viewHolder=(ViewHolder)holder;
            viewHolder.tvTest.setText(mItemDataList.get(i).content);
        }

        @Override
        public BaseViewHolder createViewHolder(View view) {
            return new ViewHolder(view);
        }

        @Override
        public int getLayout() {
            return R.layout.item_test;
        }

        /**
         * This class contains all butterknife-injected Views & Layouts from layout file 'item_test.xml'
         * for easy to all layout elements.
         *
         * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
         */
        class ViewHolder extends BaseViewHolder{
            @Bind(R.id.tv_test)
            TextView tvTest;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
