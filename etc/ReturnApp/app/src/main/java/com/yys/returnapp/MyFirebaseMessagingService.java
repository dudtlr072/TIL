package com.yys.returnapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


/**
 * Created by YYS on 2018-01-11.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            String title = remoteMessage.getData().get("title");
            String message = remoteMessage.getData().get("msg");
            handleNow(title, message);
        }
    }

//    private void scheduleJob() {
//        Log.d(TAG, "scheduleJob()");
//        // [START dispatch_job]
//        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
//        Job myJob = dispatcher.newJobBuilder()
//                .setService(MyJobService.class)
//                .setTag("my-job-tag")
//                .build();
//        dispatcher.schedule(myJob);
//        // [END dispatch_job]
//    }

    private void handleNow(String title, String message) {
        Log.d(TAG, "Short lived task is done.");
        sendNotification("시료 반납");

        Intent intent = new Intent(this, DialogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("title", title);
        intent.putExtra("message", message);
        startActivity(intent);
    }

    private void sendNotification(String messageBody) {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
//                PendingIntent.FLAG_ONE_SHOT);

        String channelId = "channel_id";
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Message")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setVibrate(new long[]{1, 1000});

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0, notificationBuilder.build());
    }
}
