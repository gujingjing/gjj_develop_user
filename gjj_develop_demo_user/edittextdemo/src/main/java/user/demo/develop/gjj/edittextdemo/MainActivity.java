package user.demo.develop.gjj.edittextdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.TextView;

import com.xw.repo.xedittext.XEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 自定义输入框分隔符
 * https://github.com/gujingjing/XEditText-master
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.default_edit_text)
    XEditText defaultEditText;
    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.custom_edit_text)
    XEditText customEditText;
    @Bind(R.id.text2)
    TextView text2;
    @Bind(R.id.ios_style_edit_text)
    XEditText iosStyleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //默认
        defaultEditText.setOnTextChangeListener(new XEditText.OnTextChangeListener() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                text1.setText(defaultEditText.getNonSeparatorText());
            }
        });
        //自定义分隔符
        customEditText.setPattern(new int[]{4, 4, 4, 4});///设置一共有几个段的分隔符  4555-5555-5555-5555
        customEditText.setSeparator("-");//设置自定义的分隔符


        customEditText.setOnTextChangeListener(new XEditText.OnTextChangeListener() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                text2.setText(customEditText.getNonSeparatorText());
            }
        });
    }
}
