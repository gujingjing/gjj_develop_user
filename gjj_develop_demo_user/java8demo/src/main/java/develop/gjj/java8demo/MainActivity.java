package develop.gjj.java8demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * http://www.jb51.net/article/48304.htm
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        一、接口的默认方法
        interfaceInfo();
        //Lambda 表达式  累死就是一个匿名内部类
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        //一个大括号表示一个方法----有方法的需要添加括号
        Collections.sort(names,(String s,String ss)->{
            return ss.compareTo(s);
        });
        //没有方法的可以神略括号
        Collections.sort(names,(a,b)->b.compareTo(a));


        //三、函数式接口
        InterTest test=(a)->a/1.2;

    }
    public void interfaceInfo(){

    }
//    Java 8允许我们给接口添加一个非抽象的方法实现，只需要使用 default关键字即可，这个特征又叫做扩展方法
    interface InterTest{
        double getDouble(int a);
        //扩展方法
    default boolean setDouble(){
        return true;
    }
    }
}
