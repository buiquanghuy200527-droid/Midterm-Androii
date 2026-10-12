package com.example.midterm_ai.music

import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import com.example.midterm_ai.widget.MusicWidgetProvider

object MusicPlayerManager {

    data class Track(val title: String, val uri: Uri)

    private var mediaPlayer: MediaPlayer? = null
    private var playlist: List<Track> = emptyList()
    private var currentTrackIndex = 0
    var isPlaying = false
        private set

    fun initPlaylist(context: Context) {
        if (playlist.isNotEmpty()) return

        val defaultUri1 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val defaultUri2 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        playlist = listOf(
            Track("Chill Melody - Track 01", defaultUri1),
            Track("Acoustic Rhythm - Track 02", defaultUri2)
        )
    }

    fun getCurrentTrack(): Track? {
        if (playlist.isEmpty()) return null
        return playlist[currentTrackIndex % playlist.size]
    }

    fun playPause(context: Context) {
        initPlaylist(context)
        val track = getCurrentTrack() ?: return

        if (mediaPlayer == null) {
            try {
                mediaPlayer = MediaPlayer.create(context, track.uri)
                mediaPlayer?.setOnCompletionListener {
                    nextTrack(context)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        if (isPlaying) {
            mediaPlayer?.pause()
            isPlaying = false
        } else {
            mediaPlayer?.start()
            isPlaying = true
        }

        MusicWidgetProvider.updateMusicWidget(context)
    }

    fun nextTrack(context: Context) {
        initPlaylist(context)
        if (playlist.isEmpty()) return

        stop()
        currentTrackIndex = (currentTrackIndex + 1) % playlist.size
        playPause(context)
    }

    fun previousTrack(context: Context) {
        initPlaylist(context)
        if (playlist.isEmpty()) return

        stop()
        currentTrackIndex = if (currentTrackIndex - 1 < 0) playlist.size - 1 else currentTrackIndex - 1
        playPause(context)
    }

    fun stop() {
        try {
            mediaPlayer?.stop()
            mediaPlayer?.release()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mediaPlayer = null
            isPlaying = false
        }
    }
}
