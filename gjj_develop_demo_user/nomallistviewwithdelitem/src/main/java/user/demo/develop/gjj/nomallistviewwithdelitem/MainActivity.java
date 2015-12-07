package user.demo.develop.gjj.nomallistviewwithdelitem;

import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import user.demo.develop.gjj.nomallistviewlib.rightSwipe.SwipeMenu;
import user.demo.develop.gjj.nomallistviewlib.rightSwipe.SwipeMenuCreator;
import user.demo.develop.gjj.nomallistviewlib.rightSwipe.SwipeMenuItem;
import user.demo.develop.gjj.nomallistviewlib.rightSwipe.SwipeMenuListView;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.swiListView)
    SwipeMenuListView swiListView;
//    @Bind(R.id.refresh)
//    MaterialRefreshLayout refresh;
    private List<ApplicationInfo> mAppList;
    private AppAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRefresh();
        initListView();
        initItemType();
        initListener();
    }
    public void initListener(){
        //点击条目事件
        swiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"点击了",Toast.LENGTH_SHORT).show();
            }
        });
        //长按条目事件
        swiListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"长按了",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //设置特别的状态的条目
        swiListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){//index是用来标记是第几个项目
                    case 0:
                        switch (position%3){
                            case 0:
                                Toast.makeText(MainActivity.this,"喜欢的",Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(MainActivity.this,"收藏的",Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(MainActivity.this,"提醒的",Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }

                        break;
                    case 1://删除的
                        mAppList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this,"删除了第"+position+"个条目",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
    /**
     * 设置listView的右边的状态条目
     */
    public void initItemType(){
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // Create different menus depending on the view type
                switch (menu.getViewType()) {
                    case 0:
                        createMenu1(menu);
                        break;
                    case 1:
                        createMenu2(menu);
                        break;
                    case 2:
                        createMenu3(menu);
                        break;
                }
            }

            /**
             * 可以设置不同的条目个数
             */
            private void createMenu1(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0x18,
                        0x5E)));
                item1.setWidth(dp2px(90));
                item1.setIcon(R.mipmap.ic_action_favorite);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                item2.setWidth(dp2px(90));
                item2.setIcon(R.mipmap.ic_action_good);
                menu.addMenuItem(item2);
            }

            private void createMenu2(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xE0,
                        0x3F)));
                item1.setWidth(dp2px(90));
                item1.setIcon(R.mipmap.ic_action_important);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                item2.setWidth(dp2px(90));
                item2.setIcon(R.mipmap.ic_action_discard);
                menu.addMenuItem(item2);
            }

            private void createMenu3(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0x30, 0xB1,
                        0xF5)));
                item1.setWidth(dp2px(90));
                item1.setIcon(R.mipmap.ic_action_about);
                menu.addMenuItem(item1);
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                item2.setWidth(dp2px(90));
                item2.setIcon(R.mipmap.ic_action_share);
                menu.addMenuItem(item2);
            }
        };
        // set creator
        swiListView.setMenuCreator(creator);
    }
    public void initListView(){
        mAppList = getPackageManager().getInstalledApplications(0);
        mAdapter = new AppAdapter();
        swiListView.setAdapter(mAdapter);
    }
    public void initRefresh(){
//        refresh.setLoadMore(false);
//        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
//            @Override
//            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
//                new android.os.Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refresh.finishRefresh();
//                        refresh.finishRefreshLoadMore();
//                    }
//                }, 3000);
//            }
//
//            @Override
//            public void onfinish() {
//                super.onfinish();
////                refresh.finishRefresh();
////                refresh.finishRefreshLoadMore();
//            }
//
//            @Override
//            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
//                super.onRefreshLoadMore(materialRefreshLayout);
//            }
//        });
    }
    class AppAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public ApplicationInfo getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            // menu type count
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            // current menu type
            return position % 3;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.item_list_app, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            ApplicationInfo item = getItem(position);
            holder.iv_icon.setImageDrawable(item.loadIcon(getPackageManager()));
            holder.tv_name.setText(item.loadLabel(getPackageManager()));
            return convertView;
        }

        class ViewHolder {
            ImageView iv_icon;
            TextView tv_name;

            public ViewHolder(View view) {
                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                view.setTag(this);
            }
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
