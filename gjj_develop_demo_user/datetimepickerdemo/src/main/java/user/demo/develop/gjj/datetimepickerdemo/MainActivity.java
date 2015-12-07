package user.demo.develop.gjj.datetimepickerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 时间选择器（扩展-城市选择器）
 * https://github.com/gujingjing/Android-PickerView
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_show)
    TextView tvShow;
    @Bind(R.id.show)
    Button show;
    //时间选择器
    TimePickerView pvTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR);
        pvTime.setTime(new Date());
        pvTime.setCancelable(true);

        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tvShow.setText(getTime(date));
            }
        });

        //弹出时间选择器
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show();
            }
        });
    }
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
