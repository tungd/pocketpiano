package com.pocketpiano.pocketpiano.exercises;


import com.pocketpiano.pocketpiano.instruments.PianoNodes;

public class Tile {

    public final PianoNodes.Note note;
    public final long time;

    public Tile(PianoNodes.Note note, long time) {
        this.note = note;
        this.time = time;
    }
}
