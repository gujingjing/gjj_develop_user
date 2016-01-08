package mvp.gjj.filedowndemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：gjj on 2016/1/8 15:34
 * 邮箱：Gujj512@163.com
 */
public class SingleTaskTestActivity extends Activity {
    @Bind(R.id.start_btn_1)
    Button startBtn1;
    @Bind(R.id.pause_btn_1)
    Button pauseBtn1;
    @Bind(R.id.delete_btn_1)
    Button deleteBtn1;
    @Bind(R.id.progressBar_1)
    ProgressBar progressBar1;
    @Bind(R.id.start_btn_2)
    Button startBtn2;
    @Bind(R.id.pause_btn_2)
    Button pauseBtn2;
    @Bind(R.id.delete_btn_2)
    Button deleteBtn2;
    @Bind(R.id.progressBar_2)
    ProgressBar progressBar2;

    private String savePath1;
    private String savePath2;

    private int downId1,downId2;
    private String[]fileUrls={
            // 随机小资源一般不超过10
            "http://download.1yd.me/apk/coach/1ydcoach-3.0.4.apk"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        ButterKnife.bind(this);

        savePath1 = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "tmp1";//在tmp1下建立文件夹
        savePath2 = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "tmp2";
    }
    @OnClick({R.id.start_btn_1,R.id.pause_btn_1,R.id.delete_btn_1,R.id.start_btn_2,R.id.pause_btn_2,R.id.delete_btn_2})void onclick(View view){
        switch (view.getId()){
            case R.id.start_btn_1:
                downId1= FileDownloader.getImpl()
                        .create(fileUrls[0])
                        .setPath(savePath1)
                        .setCallbackProgressTimes(500)//设置progress最大回调次数
                        .setListener(new FileDownloadSampleListener(){//sample里面的方法自定义---listener里面的方法比较多

                            @Override
                            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                                super.progress(task, soFarBytes, totalBytes);
                                progressBar1.setMax(totalBytes);
                                progressBar1.setProgress(soFarBytes);
                            }

                            @Override
                            protected void completed(BaseDownloadTask task) {
                                super.completed(task);
                                progressBar1.setProgress(task.getSoFarBytes());
                                Toast.makeText(SingleTaskTestActivity.this,"completed",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                                super.paused(task, soFarBytes, totalBytes);
                                Toast.makeText(SingleTaskTestActivity.this,"paused",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            protected void error(BaseDownloadTask task, Throwable e) {
                                super.error(task, e);
                                Toast.makeText(SingleTaskTestActivity.this,"error=="+e.toString(),Toast.LENGTH_SHORT).show();
                                Log.e("error==",e.toString());
                            }
                        })
                        .start();

                break;
            case R.id.pause_btn_1:
                FileDownloader.getImpl().pause(downId1);
                break;
            case R.id.delete_btn_1:
                File file=new File(savePath1);
                if(file.exists()){
                    file.delete();
                }
                break;
            case R.id.start_btn_2:
                downId2= FileDownloader.getImpl()
                        .create(fileUrls[0])
                        .setPath(savePath2)
                        .setCallbackProgressTimes(500)//设置progress最大回调次数
                        .setListener(new FileDownloadSampleListener(){//sample里面的方法自定义---listener里面的方法比较多

                            @Override
                            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                                super.progress(task, soFarBytes, totalBytes);
                                progressBar2.setMax(totalBytes);
                                progressBar2.setProgress(soFarBytes);
                            }

                            @Override
                            protected void completed(BaseDownloadTask task) {
                                super.completed(task);
                                progressBar2.setProgress(task.getSoFarBytes());
                                Toast.makeText(SingleTaskTestActivity.this,"completed",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                                super.paused(task, soFarBytes, totalBytes);
                                Toast.makeText(SingleTaskTestActivity.this,"paused",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            protected void error(BaseDownloadTask task, Throwable e) {
                                super.error(task, e);
                                Toast.makeText(SingleTaskTestActivity.this,"error=="+e.toString(),Toast.LENGTH_SHORT).show();
                                Log.e("error==",e.toString());
                            }
                        })
                        .start();
                break;
            case R.id.pause_btn_2:
                FileDownloader.getImpl().pause(downId2);
                break;
            case R.id.delete_btn_2:
                File file2=new File(savePath2);
                if(file2.exists()){
                    file2.delete();
                }
                break;
        }
    }
}
