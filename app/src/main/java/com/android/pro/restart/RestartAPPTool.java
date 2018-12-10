package com.android.pro.restart;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by： lwq.
 * Created Time: 2018/12/10 14:03
 * Description：此工具类用来重启APP，只是单纯的重启，不做任何处理。
 */
public class RestartAPPTool {

    /**
     *  重启APP服务
     * @param context
     * @param Delayed 延迟多少秒
     */
    public static void restartApp(Context context, long Delayed){
        Intent intent = new Intent(context,KillSelfService.class);
        intent.putExtra("PackageName",context.getPackageName());
        intent.putExtra("Delayed",Delayed);
        Log.d("TAG","启动重启KillSelfService服务");
        context.startService(intent);
    }

    /**
     * 重启APP
     * @param context˛
     */
    public static void restartAPP(Context context){
        restartApp(context,1500);
    }
}
