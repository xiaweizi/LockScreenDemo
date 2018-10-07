package com.example.xiaweizi.lockscreendemo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

public class LockScreenService extends Service {
    private static final String TAG = "LockScreen::";
    private LockScreenBroadcastReceiver receiver;

    public LockScreenService() {
        Log.i(TAG, "LockScreenService: 构造函数");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: 服务已开启");
        receiver = new LockScreenBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class LockScreenBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), Intent.ACTION_SCREEN_ON)) {
                Log.i(TAG, "onReceive: 收到锁屏广播");
                Intent lockScreen = new Intent(LockScreenService.this, LockScreenActivity.class);
                lockScreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(lockScreen);
            } else if (TextUtils.equals(intent.getAction(), Intent.ACTION_SCREEN_OFF)) {
                Log.i(TAG, "onReceive: 收到解锁广播");
            }
        }
    }
}
