package com.android.pro.restart;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by： lwq.
 * Created Time: 2018/12/10 15:38
 * Description：该服务只用来让APP重启，IntentService用完即走
 */
public class RestartIntentService extends IntentService {

    private static long stopDelayed = 2000;
    private Handler mHandler;
    private String PackageName;


    public RestartIntentService(){
        super("RestartIntentService");
        mHandler = new Handler();
    }

    public static void start(Context context, long Delayed) {
        Intent intent = new Intent(context, RestartIntentService.class);
        intent.putExtra("PackageName",context.getPackageName());
        intent.putExtra("Delayed",Delayed);
        context.startService(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        stopDelayed = intent.getLongExtra("Delayed",2000);
        PackageName = intent.getStringExtra("PackageName");
        mHandler.postDelayed(() -> {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
            startActivity(launchIntent);
            Log.d("TAG","重启APP成功,包名："+PackageName);
        },stopDelayed);

    }

    /**
     * 重启APP
     * @param context˛
     */
    public static void restartAPP(Context context){
        start(context,1500);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandler!=null){
            mHandler = null;
        }
    }
}
