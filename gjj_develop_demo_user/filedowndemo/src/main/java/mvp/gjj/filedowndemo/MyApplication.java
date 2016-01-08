package mvp.gjj.filedowndemo;

import android.app.Application;

import com.liulishuo.filedownloader.FileDownloader;

/**
 * 作者：gjj on 2016/1/8 16:14
 * 邮箱：Gujj512@163.com
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 不耗时，做一些简单初始化准备工作，不会启动下载进程
        FileDownloader.init(this);
    }
}
