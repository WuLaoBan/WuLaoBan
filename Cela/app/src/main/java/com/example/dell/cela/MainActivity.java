package com.example.dell.cela;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/*

 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消他的toolbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }
}
