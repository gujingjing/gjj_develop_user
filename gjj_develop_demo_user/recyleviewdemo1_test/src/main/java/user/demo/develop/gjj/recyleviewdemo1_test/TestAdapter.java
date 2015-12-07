package user.demo.develop.gjj.recyleviewdemo1_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：gjj on 2015/12/7 10:51
 * 邮箱：Gujj512@163.com
 */
public class TestAdapter extends RecyclerView.Adapter {
    private List<TestBean> list;
    private Context context;

    public TestAdapter(Context context, List<TestBean> list) {
        this.list = list;
        this.context = context;
    }

    /**
     * 创建viewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_test, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 设置viewHolder中的数据
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder=(ViewHolder)holder;
        viewHolder.tvTest.setText(list.get(position).content);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_test.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_test)
        TextView tvTest;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
