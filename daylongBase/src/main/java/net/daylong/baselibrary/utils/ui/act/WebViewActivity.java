package net.daylong.baselibrary.utils.ui.act;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import net.daylong.baselibrary.utils.MyLogUtil;
import net.daylong.baselibrary.utils.ui.ToastUtil;
import net.daylong.baselibrary.view.DrawableUtils;
import net.daylong.daylongbase.R;

import net.daylong.baselibrary.listener.MyWebViewClient;


/**
 * @author
 * @Company 广州大龙网络科技有限公司
 * @Description
 * @Date 2020/1/9
 * @Version 1.0
 */
public class WebViewActivity extends BaseActivity {

    public static void start(Activity activity, String name, String url) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    @Override
    protected Integer getLayoutId() {
        return R.layout.act_web_view;
    }

    protected WebView mWebView;
    protected String url;

    @Override
    protected void initView() {
        super.initView();
        mWebView = findViewById(R.id.webView);
        ImageButton btn = findViewById(R.id.back);
        btn.setImageResource(DrawableUtils.getDrawableByName("img_base_back"));
        btn.setBackgroundColor(Color.TRANSPARENT);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();


        Intent intent = getIntent();


//        mWebView = findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);

        url = intent.getStringExtra("url");

        //重新加载 点击网页里面的链接还是在当前的webview里跳转。不跳到浏览器那边
        mWebView.setWebViewClient(new MyWebViewClient());
        //支持js
        //支持js
        // 解决图片不显示
        mWebView.getSettings().setBlockNetworkImage(false);
        mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        //自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        //设置可以支持缩放
        mWebView.getSettings().setSupportZoom(false);
        //扩大比例的缩放
        mWebView.getSettings().setUseWideViewPort(false);
        //设置是否出现缩放工具
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                // 处理按钮点击事件
                if (message.equals("Button was clicked")) {
                    // 在这里处理按钮点击事件
                    ToastUtil.show("点击了确认按钮");
                }
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                MyLogUtil.e("webView->", view.getUrl());


            }
        });
        mWebView.loadUrl(url);


    }


}
