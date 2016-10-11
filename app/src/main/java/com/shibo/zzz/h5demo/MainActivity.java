package com.shibo.zzz.h5demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{
    private WebView mWebView;
    private WebSettings webSettings;
    private Button mButton;
    private WebAppInterface appInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.wv);
        mButton= (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(this);


        webSettings=mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/index.html");
       // mWebView.addJavascriptInterface(new WebAppInterface(this),"app");

        appInterface=new WebAppInterface(this);
        mWebView.addJavascriptInterface(appInterface,"app");

    }

    @Override
    public void onClick(View v) {
appInterface.showName("yoyo");
    }

    class WebAppInterface{
        private Context context;
        public WebAppInterface(Context context){
            this.context=context;
        }
        @JavascriptInterface
    public void sayHello(String name){
            Toast.makeText(context,"name="+name,Toast.LENGTH_LONG).show();


    }
        public void showName(String name){
            mWebView.loadUrl("javascript:showName('"+name+"')");
        }




    }


}
