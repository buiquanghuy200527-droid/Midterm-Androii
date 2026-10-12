package com.example.midterm_ai.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.midterm_ai.R
import com.example.midterm_ai.music.MusicPlayerManager

class MusicWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        when (intent.action) {
            ACTION_PLAY_PAUSE -> {
                MusicPlayerManager.playPause(context)
            }
            ACTION_NEXT -> {
                MusicPlayerManager.nextTrack(context)
            }
            ACTION_PREV -> {
                MusicPlayerManager.previousTrack(context)
            }
        }
    }

    companion object {
        const val ACTION_PLAY_PAUSE = "com.example.midterm_ai.ACTION_PLAY_PAUSE"
        const val ACTION_NEXT = "com.example.midterm_ai.ACTION_NEXT"
        const val ACTION_PREV = "com.example.midterm_ai.ACTION_PREV"

        fun updateWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val views = RemoteViews(context.packageName, R.layout.widget_music)

            val currentTrack = MusicPlayerManager.getCurrentTrack()
            val isPlaying = MusicPlayerManager.isPlaying

            if (currentTrack != null) {
                views.setTextViewText(R.id.tv_music_title, currentTrack.title)
                views.setTextViewText(R.id.tv_music_status, if (isPlaying) "Đang phát..." else "Đã tạm dừng")
            } else {
                views.setTextViewText(R.id.tv_music_title, "Music Player")
                views.setTextViewText(R.id.tv_music_status, "Chưa chọn bài")
            }

            views.setImageViewResource(
                R.id.btn_music_play_pause,
                if (isPlaying) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play
            )

            // Intent for Play/Pause
            val playIntent = Intent(context, MusicWidgetProvider::class.java).apply {
                action = ACTION_PLAY_PAUSE
            }
            val playPendingIntent = PendingIntent.getBroadcast(
                context, 0, playIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.btn_music_play_pause, playPendingIntent)

            // Intent for Next
            val nextIntent = Intent(context, MusicWidgetProvider::class.java).apply {
                action = ACTION_NEXT
            }
            val nextPendingIntent = PendingIntent.getBroadcast(
                context, 1, nextIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.btn_music_next, nextPendingIntent)

            // Intent for Prev
            val prevIntent = Intent(context, MusicWidgetProvider::class.java).apply {
                action = ACTION_PREV
            }
            val prevPendingIntent = PendingIntent.getBroadcast(
                context, 2, prevIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.btn_music_prev, prevPendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        fun updateMusicWidget(context: Context) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val componentName = ComponentName(context, MusicWidgetProvider::class.java)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(componentName)

            for (appWidgetId in appWidgetIds) {
                updateWidget(context, appWidgetManager, appWidgetId)
            }
        }
    }
}
