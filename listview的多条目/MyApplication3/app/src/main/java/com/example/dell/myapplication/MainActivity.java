package com.example.dell.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private ProgressBar progressbar;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        imageView = (ImageView) findViewById(R.id.imageView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask<Void, Void, Bitmap> asyncTask = new AsyncTask<Void, Void, Bitmap>() {
                    //在子线程之前开启
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progressbar.setVisibility(View.VISIBLE);

                    }

                    @Override
                    protected void onProgressUpdate(Void... values) {
                        super.onProgressUpdate(values);
                    }

                    //此方法在子线程中运行
                    @Override
                    protected Bitmap doInBackground(Void... voids) {
                        try {
                            String path ="http://img4.duitang.com/uploads/item/201410/14/20141014123536_aHu4Y.jpeg";

                            URL url = new URL(path);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(8000);
                            if(connection.getResponseCode() == 200){
                                InputStream inputStream = connection.getInputStream();
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                return bitmap;
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        return null;
                    }
                    //此方法在主线程执行
                    @Override
                    protected void onPostExecute(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                        progressbar.setVisibility(View.GONE);
                        super.onPostExecute(bitmap);
                    }
                };
                asyncTask.execute();
            }
        });

    }
}
