package user.demo.develop.gjj.progressdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.bigkoo.svprogresshud.SVProgressHUD;

/**
 * 加载进度条
 * https://github.com/gujingjing/Android-SVProgressHUD-master
 */
public class MainActivity extends AppCompatActivity {
    int progress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void show(View view){
        SVProgressHUD.show(this);
    }
    public void showWithMaskType(View view){
        SVProgressHUD.showWithMaskType(this, SVProgressHUD.SVProgressHUDMaskType.None);
//        SVProgressHUD.showWithMaskType(this,SVProgressHUD.SVProgressHUDMaskType.Black);
//        SVProgressHUD.showWithMaskType(this, SVProgressHUD.SVProgressHUDMaskType.BlackCancel);
//        SVProgressHUD.showWithMaskType(this, SVProgressHUD.SVProgressHUDMaskType.Clear);
//        SVProgressHUD.showWithMaskType(this, SVProgressHUD.SVProgressHUDMaskType.ClearCancel);
//        SVProgressHUD.showWithMaskType(this, SVProgressHUD.SVProgressHUDMaskType.Gradient);
//        SVProgressHUD.showWithMaskType(this, SVProgressHUD.SVProgressHUDMaskType.GradientCancel);
    }

    /**
     * 只显示加载框
     */
    public void showWithStatus(View view){
        SVProgressHUD.showWithStatus(this, "加载中...");
    }

    /**
     * 显示文字提示加载框
     */
    public void showInfoWithStatus(View view){
        SVProgressHUD.showInfoWithStatus(this, "这是提示", SVProgressHUD.SVProgressHUDMaskType.None);
    }

    /**
     * 显示成功的提示
     */
    public void showSuccessWithStatus(View view){
        SVProgressHUD.showSuccessWithStatus(this, "恭喜，提交成功！");
    }
    /**
     * 显示失败的提示
     */
    public void showErrorWithStatus(View view){
        SVProgressHUD.showErrorWithStatus(this, "不约，叔叔我们不约～", SVProgressHUD.SVProgressHUDMaskType.GradientCancel);
    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress = progress + 5;
            if (SVProgressHUD.getProgressBar(MainActivity.this).getMax() != SVProgressHUD.getProgressBar(MainActivity.this).getProgress()) {
                SVProgressHUD.getProgressBar(MainActivity.this).setProgress(progress);
                SVProgressHUD.setText(MainActivity.this, "进度 "+progress+"%");

                mHandler.sendEmptyMessageDelayed(0,500);
            }
            else{
                SVProgressHUD.dismiss(MainActivity.this);
                SVProgressHUD.getProgressBar(MainActivity.this).setProgress(0);
            }

        }
    };
    public void showWithProgress(View view){
        SVProgressHUD.showWithProgress(this, "进度 "+progress+"%", SVProgressHUD.SVProgressHUDMaskType.Black);
        progress = 0;
        mHandler.sendEmptyMessageDelayed(0,500);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            if(SVProgressHUD.isShowing(this)){
                SVProgressHUD.dismiss(this);
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
