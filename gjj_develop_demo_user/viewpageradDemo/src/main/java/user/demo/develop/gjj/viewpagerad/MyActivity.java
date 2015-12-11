package user.demo.develop.gjj.viewpagerad;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;


import user.demo.develop.gjj.viewpagerad.autoScrollViewPager.TextFragment;

public class MyActivity extends ActionBarActivity {

    private ViewPager contentPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentPager = (ViewPager) findViewById(R.id.pager);
        contentPager.setOffscreenPageLimit(2);
        contentPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if (i == 0) {
                    return new AutoScrollPagerFragment();
                }
                return TextFragment.newInstance("Fragment " + i);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }

}
