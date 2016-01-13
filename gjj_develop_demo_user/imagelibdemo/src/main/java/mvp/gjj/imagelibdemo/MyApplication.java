package mvp.gjj.imagelibdemo;

import android.app.Application;
import android.graphics.Color;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import mvp.gjj.imagelibdemo.loader.GlideImageLoader;

/**
 * 作者：gjj on 2016/1/9 11:00
 * 邮箱：Gujj512@163.com
 */
public class MyApplication extends Application {
    private MyApplication mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
       initImageLib();
    }
    public void initImageLib(){
        //建议在application中配置
        //设置主题
        //DEFAULT、DARK、CYAN、ORANGE、GREEN、TEAL---otherThem()->其他自定义的主题;
//        ThemeConfig theme = ThemeConfig.CYAN;
//
//        //配置功能
//        FunctionConfig functionConfig = new FunctionConfig.Builder()
//                .setEnableCamera(true)//开启相机功能
//                .setEnableEdit(true)//开启编辑功能
//                .setEnableCrop(true)//启动强制裁剪功能,一进入编辑页面就开启图片裁剪，不需要用户手动点击裁剪，此功能只针对单选操作
//                .setEnableRotate(true)//开启选择功能
//                .setCropSquare(true)//裁剪正方形
////                    .setCropWidth(width)//裁剪的宽度
////                    .setCropHeight(height)////裁剪的高度
//                .setEnablePreview(true)//是否开启预览功能
//                .build();
//        CoreConfig coreConfig = new CoreConfig.Builder(this, new GlideImageLoader(), theme)
//                .setDebug(BuildConfig.DEBUG)
//                .setFunctionConfig(functionConfig)
//                .build();
//        GalleryFinal.init(coreConfig);
    }
    public void otherThem(){
        //        ThemeConfig theme = new ThemeConfig.Builder()
//                .setTitleBarBgColor(Color.rgb(0xFF, 0x57, 0x22))//标题栏背景颜色
//                .setTitleBarTextColor(Color.BLACK)//标题栏文本字体颜色
//                .setTitleBarIconColor(Color.BLACK)//标题栏icon颜色，如果设置了标题栏icon，设置setTitleBarIconColor将无效
//                .setFabNornalColor(Color.RED)//设置Floating按钮Nornal状态颜色
//                .setFabPressedColor(Color.BLUE)//设置Floating按钮Pressed状态颜色
//                .setCheckNornalColor(Color.WHITE)//选择框未选颜色
//                .setCheckSelectedColor(Color.BLACK)//选择框选中颜色
//                .setIconBack(R.mipmap.ic_action_previous_item)//设置返回按钮icon
//                .setIconRotate(R.mipmap.ic_action_repeat)//设置选择icon
//                .setIconCrop(R.mipmap.ic_action_crop)//设置裁剪icon
//                .setIconCamera(R.mipmap.ic_action_camera)//设置相机icon
//                .build();
//        themeConfig = theme;
    }
}
