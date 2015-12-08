package user.demo.develop.gjj.datetimenewdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.feezu.liuli.timeselector.TimeSelector;
import org.feezu.liuli.timeselector.Utils.DateUtil;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TimeSelector timeSelector;
    private final String FORMAT_STR = "yyyy-MM-dd HH:mm";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeSelector=new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(long time) {
                Toast.makeText(getApplicationContext(), DateUtil.format(new Date(time), FORMAT_STR), Toast.LENGTH_LONG).show();
            }
        },DateUtil.format(new Date(System.currentTimeMillis()), FORMAT_STR), "2050-11-29 21:54",TimeSelector.Type.ALL);
    }
    public void show(View v) {
        timeSelector.show();
    }
}
