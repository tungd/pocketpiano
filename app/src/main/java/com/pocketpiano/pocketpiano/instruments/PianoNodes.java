package com.pocketpiano.pocketpiano.instruments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by quanghuy on 4/23/16.
 */
public class PianoNodes {

    Context context;
    MediaPlayer mediaPlayer;
    Map<Note, MediaPlayer> players = new HashMap<>();


    public PianoNodes(Context context){
        this.context = context;
    }
    public enum Note {
        A1, A,
        B1, B,
        C1, C,
        D1, D,
        E1, E,
        F1, F,
        G1, G
    }

    public void play(Note note) {
        if (!players.containsKey(note)) {
            MediaPlayer player = new MediaPlayer();
            try {
                player.setDataSource(context, Uri.parse("android.resource://com.pocketpiano.pocketpiano/" +
                        context.getResources().getIdentifier(note.toString().toLowerCase(), "raw", context.getPackageName())));
                player.prepare();
                players.put(note, player);
            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }

        MediaPlayer player = players.get(note);
        if (player != null && !player.isPlaying()) {
            player.start();
        }
    }

    public void playRandomNode() {
        Note note = Note.values()[(int) (Math.random() * Note.values().length)];
        this.play(note);
    }
}
