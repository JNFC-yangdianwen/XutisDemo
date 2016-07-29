package com.example.yangdianwen.xutisdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
/***
 *使用xutils框架进行网络请求 关联布局id
 *使用其中的viewutils关联id ViewUtils.inject()方法进行初始化
 *使用httpUtils进行网络请求,使用该类会出现apache的httpRequestBase类未找到的异常,
 *  解决方案：在build.gradle中添加以下代码：
 *       android {
 *                 useLibrary 'org.apache.http.legacy'
 *                 compileSdkVersion 23
 *                 buildToolsVersion "23.0.1"
 *               }
 */

public class MainActivity extends AppCompatActivity {
//"http://www.lidroid.com",
    @ViewInject(R.id.tv) TextView testTextView;
    @ViewInject(R.id.tv1) TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化ViewUtils
        ViewUtils.inject(this);
        //初始化HttpUtils
        HttpUtils http = new HttpUtils();
        //调用send方法（三个参数）（请求方式，url，请求回调（RequestCallBack））
        http.send(HttpRequest.HttpMethod.GET,
                "http://www.lidroid.com",
                new RequestCallBack<String>(){
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        testTextView.setText(current + "/" + total);
                    }
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        textView.setText(responseInfo.result);
                    }
                    @Override
                    public void onStart() {
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });
    }
}
