package com.pocketpiano.pocketpiano.instruments;

import android.content.Context;
import android.media.JetPlayer;
import com.pocketpiano.pocketpiano.R;

/**
 * Created by quanghuy on 4/23/16.
 */
public class PianoNodes {

    Context context;
    JetPlayer jetPlayer = JetPlayer.getJetPlayer();

    public PianoNodes(Context context){
        this.context = context;
    }
    enum Note {
        A1, A1_, A2, A2_, A3, A3_, A4, A4_,
        B1, B2, B3, B4,
        C1, C1_, C2, C2_, C3, C3_, C4, C4_,
        D1, D1_, D2, D2_, D3, D3_, D4, D4_,
        E1, E2, E3, E4,
        F1, F1_, F2, F2_, F3, F3_, F4, F4_,
        G1, G1_, G2, G2_, G3, G3_, G4, G4_
    }

    public void play(Note note) {

        jetPlayer.loadJetFile(String.format("android.resource://" + context.getPackageName() + "/raw/"+ "/%s.mid", note.name().toLowerCase()));
    }

    public void startMedia(){
        this.jetPlayer.play();
    }

}
