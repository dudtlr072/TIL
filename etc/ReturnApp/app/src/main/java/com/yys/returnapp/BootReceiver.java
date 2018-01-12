package com.yys.returnapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by YYS on 2018-01-12.
 */

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d("yys", "[BootReceiver] action : " + action);
        if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            Toast.makeText(context, "Subscribed firebase messaging to topic.", Toast.LENGTH_LONG).show();
            FirebaseMessaging.getInstance().subscribeToTopic("notice");
        }
    }
}
