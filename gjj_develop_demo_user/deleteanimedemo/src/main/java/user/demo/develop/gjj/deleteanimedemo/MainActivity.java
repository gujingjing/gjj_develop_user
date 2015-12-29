package user.demo.develop.gjj.deleteanimedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import tyrantgit.explosionfield.ExplosionField;

/**
 * 删除图片
 * https://github.com/gujingjing/ExplosionField
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.iv1)
    ImageView iv1;
    @Bind(R.id.iv2)
    ImageView iv2;
    @Bind(R.id.tv_auto)
    TextView tvAuto;
    private ExplosionField mExplosionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mExplosionField = ExplosionField.attach2Window(this);
        addListener(findViewById(R.id.iv1));
        addListener(findViewById(R.id.iv2));
    }

    private void addListener(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                addListener(parent.getChildAt(i));
            }
        } else {
            root.setClickable(true);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    switch (v.getId()){
//                        case R.id.iv1:
//
//                            break;
//                        case R.id.iv2://英航卡
//
//                            break;
//                    }
                    mExplosionField.explode(v);
                    v.setOnClickListener(null);
                }
            });
        }
    }
}
