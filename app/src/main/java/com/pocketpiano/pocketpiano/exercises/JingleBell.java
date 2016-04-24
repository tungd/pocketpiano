package com.pocketpiano.pocketpiano.exercises;

import com.pocketpiano.pocketpiano.instruments.PianoNodes;
import com.pocketpiano.pocketpiano.instruments.PianoNodes.Note;

import java.util.ArrayList;
import java.util.List;

public class JingleBell {

    class Tile {

        final PianoNodes.Note note;
        final long time;

        public Tile(PianoNodes.Note note, long time) {
            this.note = note;
            this.time = time;
        }
    }

    List<Tile> tiles;

    public JingleBell() {
        tiles = new ArrayList<>();

        tiles.add(new Tile(Note.C, 0));
        tiles.add(new Tile(Note.C, 2));
        tiles.add(new Tile(Note.C, 4));

        tiles.add(new Tile(Note.C, 8));
        tiles.add(new Tile(Note.C, 10));
        tiles.add(new Tile(Note.C, 12));

        tiles.add(new Tile(Note.C, 16));
        tiles.add(new Tile(Note.E1, 18));
        tiles.add(new Tile(Note.A, 20));
        tiles.add(new Tile(Note.C, 22));

        tiles.add(new Tile(Note.D, 26));
        tiles.add(new Tile(Note.D, 28));
        tiles.add(new Tile(Note.D, 30));

        tiles.add(new Tile(Note.D, 34));
        tiles.add(new Tile(Note.D, 36));
        tiles.add(new Tile(Note.C, 38));
        tiles.add(new Tile(Note.C, 40));

        tiles.add(new Tile(Note.C, 44));
        tiles.add(new Tile(Note.C, 46));
        tiles.add(new Tile(Note.C, 48));
        tiles.add(new Tile(Note.B, 50));
        tiles.add(new Tile(Note.B, 52));
        tiles.add(new Tile(Note.C, 54));
        tiles.add(new Tile(Note.B, 56));

        tiles.add(new Tile(Note.E1, 60));
    }

    public List<Tile> tiles() {
        return tiles;
    }
}
