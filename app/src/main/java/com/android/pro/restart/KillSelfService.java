package com.android.pro.restart;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by： lwq.
 * Created Time: 2018/12/10 14:06
 * Description：该服务只用来让APP重启，重启完即自我自杀，服务需在独立进程
 */
public class KillSelfService extends Service {

    private static long stopDelayed = 2000;
    private Handler mHandler;
    private String PackageName;

    public KillSelfService(){
        mHandler = new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        stopDelayed = intent.getLongExtra("Delayed",2000);
        PackageName = intent.getStringExtra("PackageName");
        mHandler.postDelayed(() -> {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
            startActivity(launchIntent);
            Log.d("TAG","重启APP服务成功");
            KillSelfService.this.stopSelf();
        },stopDelayed);


        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
