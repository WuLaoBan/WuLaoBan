package com.example.wz.asynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/6/13 9:49
 */
public class MyTask extends AsyncTask<Object,String,String>{
    private TextView tv;
    /**
     * 后台运行方法 在子线程运行
     * @param params  参数  可变数组
     * @return
     */
    @Override
    protected String doInBackground(Object... params) {
        if(params!=null&&params.length>0){
            String urlPath = params[0]+"";
            tv = (TextView) params[1];
            publishProgress("开始解析数据");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress("开始网络请求");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String result=UrlUtils.getUrlConnect(urlPath);

            publishProgress("已经拿到数据");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("doInBackground", "doInBackground: "+result);
            return result;
        }
        return null;
    }

    /**
     * 主线程 在doInBackground运行过程中 显示完成进度
     * 在doInBackground调用一个方法激活执行
     * @param values
     */
    @Override
    protected void onProgressUpdate(String... values) {
        tv.setText(values[0]);
        super.onProgressUpdate(values);
    }


    /**
     * 主线程 拿到doInBackground的返回结果
     * 在这个可以更改UI
     * @param s
     */
    @Override
    protected void onPostExecute(String s) {
        if(tv!=null)
            tv.setText(s);
        super.onPostExecute(s);
    }
}
