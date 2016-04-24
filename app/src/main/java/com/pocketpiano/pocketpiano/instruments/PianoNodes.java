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
//        A5_, A5, A3_, A3, A4, A4_,
//        B5, B3, B4,
//        C5_, C5, C3_, C3, C4, C4_,
//        D5_, D5, D3_, D3, D4, D4_,
//        E5, E3, E4,
//        F5_, F5, F3_, F3, F4, F4_,
//        G5_, G5, G3_, G3, G4, G4_
A1, A,
        B1, B,
        C1, C,
        D, D1,
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
