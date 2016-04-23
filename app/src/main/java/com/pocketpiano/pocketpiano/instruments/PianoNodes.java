package com.pocketpiano.pocketpiano.instruments;

import android.content.Context;
import android.media.JetPlayer;
import android.media.MediaPlayer;
import android.net.Uri;
import com.pocketpiano.pocketpiano.R;

/**
 * Created by quanghuy on 4/23/16.
 */
public class PianoNodes {

    Context context;
    MediaPlayer mediaPlayer = new MediaPlayer();


    public PianoNodes(Context context){
        this.context = context;
    }
    public enum Note {
        A5_, A5, A3_, A3, A4, A4_,
        B5, B3, B4,
        C5_, C5, C3_, C3, C4, C4_,
        D5_, D5, D3_, D3, D4, D4_,
        E5, E3, E4,
        F5_, F5, F3_, F3, F4, F4_,
        G5_, G5, G3_, G3, G4, G4_
    }

    public void play(Note note) {
        int id = context.getResources().getIdentifier(note.toString().toLowerCase(), "raw", context.getPackageName());
        mediaPlayer.create(context, id);
    }

    public void startMedia(Note note){
        try {
            System.out.println("Note:" + note.toString());
            mediaPlayer.setDataSource(context, Uri.parse("android.resource://com.pocketpiano.pocketpiano/" +
                    context.getResources().getIdentifier(note.toString().toLowerCase(), "raw", context.getPackageName())));
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(onCompletionListener);
        }
        catch (Exception e) {

        }
    }

    public void playRandomNode() {
        Note note = Note.values()[(int) (Math.random() * Note.values().length)];
        this.play(note);
        this.startMedia(note);
    }

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    };
}
