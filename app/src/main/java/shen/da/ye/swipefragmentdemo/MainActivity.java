package shen.da.ye.swipefragmentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import shen.da.ye.swipefragmentdemo.wechat.WeChatHomeActivity;
import shen.da.ye.swipefragmentdemo.zhihu.ZhiHuActivity;

/**
 * @author ChenYe
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goWeChat(View view) {
        startActivity(new Intent(this, WeChatHomeActivity.class));
    }

    public void goZhiHu(View view) {
        startActivity(new Intent(this, ZhiHuActivity.class));
    }
}
