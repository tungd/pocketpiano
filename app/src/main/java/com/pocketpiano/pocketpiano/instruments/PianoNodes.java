package com.pocketpiano.pocketpiano.instruments;

import android.media.JetPlayer;
import com.pocketpiano.pocketpiano.R;

/**
 * Created by quanghuy on 4/23/16.
 */
public class PianoNodes {

    JetPlayer jetPlayer = JetPlayer.getJetPlayer();
    

    public synchronized void A1(){
        
        jetPlayer.loadJetFile("file:///sdcard/mdi/a1.mid");
        
    }

    public synchronized void A1_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/a1_.mid");
        
    }

    public synchronized void A2(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/a2.mid");
        
    }

    public synchronized void A2_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/a2_.mid");
        
    }

    public synchronized void A3(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/a3.mid");
        
    }

    public synchronized void A3_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/a3_.mid");
        
    }

    public synchronized void A4(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/a4.mid");
        
    }

    public synchronized void A4_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/a4_.mid");
        
    }

    public synchronized void G1(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/g1.mid");
        
    }

    public synchronized void G1_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/g1_.mid");
        
    }

    public synchronized void G2(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/g2.mid");
        
    }

    public synchronized void G2_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/g2_.mid");
        
    }

    public synchronized void G3(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/g3.mid");
        
    }

    public synchronized void G3_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/g3_.mid");
        
    }

    public synchronized void G4(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/g4.mid");
        
    }

    public synchronized void G4_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/g4_.mid");
        
    }

    public synchronized void F1(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/f1.mid");
        
    }

    public synchronized void F1_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/f1_.mid");
        
    }

    public synchronized void F2(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/f2.mid");
        
    }

    public synchronized void F2_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/f2_.mid");
        
    }

    public synchronized void F3(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/f3_.mid");
        
    }

    public synchronized void F3_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/f3_.mid");
        
    }

    public synchronized void F4(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/f4.mid");
        
    }

    public synchronized void F4_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/f4_.mid");
        
    }

    public synchronized void E1(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/e1.mid");
        
    }

    public synchronized void E2(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/e2.mid");
        
    }

    public synchronized void E3(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/e3.mid");
        
    }

    public synchronized void E4(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/e4.mid");
        
    }

    public synchronized void D1(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/d1.mid");
        
    }

    public synchronized void D1_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/d1_.mid");
        
    }

    public synchronized void D2(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/d2.mid");
        
    }

    public synchronized void D2_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/d2_.mid");
        
    }

    public synchronized void D3(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/d3.mid");
        
    }

    public synchronized void D3_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/d3_.mid");
        
    }

    public synchronized void D4(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/d4.mid");
        
    }

    public synchronized void D4_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/d4_.mid");
        
    }

    public synchronized void C1(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/c1.mid");
        
    }

    public synchronized void C1_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/c1_.mid");
        
    }

    public synchronized void C2(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/c2.mid");
        
    }

    public synchronized void C2_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/c2_.mid");
        
    }

    public synchronized void C3(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/c3.mid");
        
    }

    public synchronized void C3_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/c3_.mid");
        
    }

    public synchronized void C4(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/c4.mid");
        
    }

    public synchronized void C4_(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/c4_.mid");
        
    }

    public synchronized void B1(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/b1.mid");
        
    }

    public synchronized void B2(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/b2.mid");
        
    }

    public synchronized void B3(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/b3.mid");
        
    }

    public synchronized void B4(){
        jetPlayer.loadJetFile("file:///sdcard/mdi/b4.mid");
        
    }

    public void startMedia(){
        this.jetPlayer.play();
    }

}
