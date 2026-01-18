package com.example.tinythinkers;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class BackgroundMusicService extends Service {

    private MediaPlayer mediaPlayer;
    private static final String CHANNEL_ID = "music_channel";

    @SuppressLint("ForegroundServiceType")
    @Override
    public void onCreate() {
        super.onCreate();

        try {
            // Initialize MediaPlayer
            mediaPlayer = MediaPlayer.create(this, R.raw.gamesound);
            mediaPlayer.setLooping(true); // Continuous playback

            // Create notification channel (Android 8+)
            createNotificationChannel();

            // Build notification for foreground service
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("TinyThinkers Music")
                    .setContentText("Background music is playing")
                    .setSmallIcon(R.drawable.game_background) // replace with your drawable
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setOngoing(true)
                    .build();

            // Start as foreground service
            startForeground(1, notification);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return START_STICKY; // Keep service alive
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Music Service",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel.setDescription("Channel for background music");

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null; // No binding needed
    }
}
