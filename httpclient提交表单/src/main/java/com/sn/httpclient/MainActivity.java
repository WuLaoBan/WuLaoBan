package com.sn.httpclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * 13,14年,有一个很知名的网络框架HttpClient,可以提高我们开发的效率,但是在android6.0也就是API23以后,从androidSDK中移除
 * 1.配置环境,在module的build.gradle文件里添加android { useLibrary 'org.apache.http.legacy'  }
 * 注意:添加权限
 */
public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_pass;

    //用户输入的信息
    private String mName =null;
    private String mPass =null;
    private final String PathPost="http://123.206.70.44:8080/JavaWebTest/Upload_html?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pass = (EditText) findViewById(R.id.et_pass);
    }

    public void get(View view){


        submit();

        final String PathGet="http://123.206.70.44:8080/JavaWebTest/Upload_html?user="+mName+"&password="+mPass;

        new Thread(){
            public void run() {
                //1.创建httpClient对象
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                //2.创建请求对象,把网址封装到请求对象,请求网络的方式
                HttpGet httpGet = new HttpGet(PathGet);
                try {
                    //3.使用客户端对象发送请求,获取服务器的响应
                    //从response对象中拿到服务器给我们返回的信息
                    HttpResponse response = defaultHttpClient.execute(httpGet);
                    StatusLine line = response.getStatusLine();
                    //获取状态码
                    int code = line.getStatusCode();
                    //4.判断请求网络是否成功
                    if (code == 200){
                        //5.服务器通过流写给客户端的数据,把它成一个实体
                        HttpEntity entity = response.getEntity();
                        //获取输入流
                        InputStream is = entity.getContent();

                        //自己写的工具类,把字节流转换为字符流
                        final String textFromStream = Tools.getTextFromStream(is);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "往服务器上传的表单"+textFromStream, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            };
        }.start();
    }

    public void post(View view){
        //对输入框进行非空判断
        submit();

        new Thread(){
            public void run() {
                //1.创建httpClient对象
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();


                //2.创建请求对象,把网址封装到请求对象,请求网络的方式
                HttpPost httpPost = new HttpPost(PathPost);
                //3.把要提交的数据封装到Post请求体中
                BasicNameValuePair user = new BasicNameValuePair("user", mName);
                BasicNameValuePair password = new BasicNameValuePair("password", mPass);
                //4.创建一个集合,用于封装要提交的数据
                ArrayList<NameValuePair> parameters = new ArrayList<>();
                parameters.add(user);
                parameters.add(password);
                try {
                    //5.创建实体,要提交的数据封装到实体中
                    UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parameters, "utf-8");
                    //6.把实体对象封装到Post请求对象中
                    httpPost.setEntity(urlEncodedFormEntity);

                    HttpResponse response = defaultHttpClient.execute(httpPost);
                    if (response.getStatusLine().getStatusCode() == 200){
                        InputStream is = response.getEntity().getContent();
                        final String textFromStream = Tools.getTextFromStream(is);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"往服务器上传表单使用Post"+textFromStream, Toast.LENGTH_SHORT).show();
                            }
                        });


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            };
        }.start();


    }



    private void submit() {
        mName = et_name.getText().toString().trim();
        mPass = et_pass.getText().toString().trim();

        if (TextUtils.isEmpty(mName) || TextUtils.isEmpty(mPass)) {
            Toast.makeText(this, "name不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
    }



}
