package mvp.gjj.imagelibdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.HorizontalListView;
import mvp.gjj.imagelibdemo.loader.GlideImageLoader;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_open_gallery)
    Button btnOpenGallery;
    @Bind(R.id.btn_clear)
    Button btnClear;
    @Bind(R.id.lv_photo)
    HorizontalListView mLvPhoto;

    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;

    private List<PhotoInfo> mPhotoList;
    private ChoosePhotoListAdapter mChoosePhotoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPhotoList = new ArrayList<>();
        mChoosePhotoListAdapter = new ChoosePhotoListAdapter(this, mPhotoList);
        mLvPhoto.setAdapter(mChoosePhotoListAdapter);
    }

    @OnClick({R.id.btn_open_gallery, R.id.btn_clear})
    void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_gallery:
                //公共配置都可以在application中配置，这里只是为了代码演示而写在此处
                //DEFAULT、DARK、CYAN、ORANGE、GREEN、TEAL
                ThemeConfig themeConfig = ThemeConfig.CYAN;
                FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
                //图片加载器
                ImageLoader imageLoader = new GlideImageLoader();
                functionConfigBuilder.setMutiSelectMaxSize(8);//设置最大选择个数

                functionConfigBuilder.setEnableCamera(true)//开启相机功能
                        .setEnableEdit(true)//开启编辑功能
                        .setEnableCrop(true)//启动强制裁剪功能,一进入编辑页面就开启图片裁剪，不需要用户手动点击裁剪，此功能只针对单选操作
                        .setEnableRotate(true)//开启选择功能
                        .setCropSquare(true)//裁剪正方形
//                    .setCropWidth(width)//裁剪的宽度
//                    .setCropHeight(height)////裁剪的高度
                        .setEnablePreview(true)//是否开启预览功能
                        .build();

                functionConfigBuilder.setSelected(mPhotoList);//添加过滤集合
                final FunctionConfig functionConfig = functionConfigBuilder.build();
                CoreConfig coreConfig = new CoreConfig.Builder(MainActivity.this, imageLoader, themeConfig)
                        .setDebug(BuildConfig.DEBUG)
                        .setFunctionConfig(functionConfig)
                        .build();
                GalleryFinal.init(coreConfig);

                //多选
                GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                //单选
//                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                //打开照相机
//                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);
                //裁剪
//                if (new File(path).exists()) {
//                    GalleryFinal.openCrop(REQUEST_CODE_CROP, functionConfig, path, mOnHanlderResultCallback);
//                } else {
//                    Toast.makeText(MainActivity.this, "图片不存在", Toast.LENGTH_SHORT).show();
//                }
                //编辑
//                if (new File(path).exists()) {
//                    GalleryFinal.openEdit(REQUEST_CODE_EDIT, functionConfig, path, mOnHanlderResultCallback);
//                } else {
//                    Toast.makeText(MainActivity.this, "图片不存在", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.btn_clear://清理缓存
                GalleryFinal.cleanCacheFile();
                Toast.makeText(this, "清理成功(Clear success)", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                mPhotoList.addAll(resultList);
                mChoosePhotoListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };
}
