package com.example.wz.asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private final String urlPath = "http://www.tngou.net/api/cook/list?rows=30&id=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyTask task=new MyTask();
        task.execute(urlPath,findViewById(R.id.tv));
    }
}
