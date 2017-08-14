package com.example.dell.cela.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者:武锦
 * 时间:2017/8/14
 * 工程名:Cela
 */

public class toastutil {

        private static Toast toast;

        public static void showToast(Context context , String msg){
            if (toast ==null){
                toast=Toast.makeText(context, "", Toast.LENGTH_SHORT);
            }
            toast.setText(msg);
            toast.show();
        }
    }


