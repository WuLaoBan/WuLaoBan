package com.example.dell.qq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.andy.share.QQOauthUtils;
import com.bumptech.glide.Glide;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ImageView imge;
    private QQOauthUtils qqOauthUtilsl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imge = (ImageView) findViewById(R.id.imge);
        qqOauthUtilsl = new QQOauthUtils();
        imge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qqOauthUtilsl.qqLogin(MainActivity.this, new QQOauthUtils.QQCallBack() {
                    @Override
                    public void logsuccess() {

                    }

                    @Override
                    public void getuserinfo(Map<String, String> map) {

                    }

                    @Override
                    public void getUserName(String name) {

                    }

                    @Override
                    public void getImagepath(String url) {
                        Glide.with(MainActivity.this)
                                .load(url)
                                .into(imge);
                    }
                });
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        qqOauthUtilsl.onActivityResult(requestCode, resultCode, data);
    }
}
