package com.pocketpiano.pocketpiano.exercises;

import com.pocketpiano.pocketpiano.instruments.PianoNodes.Note;

import java.util.ArrayList;
import java.util.List;

public class JingleBell {

    List<Tile> tiles;

    public JingleBell() {
        tiles = new ArrayList<>();

        tiles.add(new Tile(Note.C1, 0));
        tiles.add(new Tile(Note.C1, 2));
        tiles.add(new Tile(Note.C1, 4));

        tiles.add(new Tile(Note.C1, 8));
        tiles.add(new Tile(Note.C1, 10));
        tiles.add(new Tile(Note.C1, 12));

        tiles.add(new Tile(Note.C1, 16));
        tiles.add(new Tile(Note.E1, 18));
        tiles.add(new Tile(Note.A, 20));
        tiles.add(new Tile(Note.C1, 22));

        tiles.add(new Tile(Note.D, 26));
        tiles.add(new Tile(Note.D, 28));
        tiles.add(new Tile(Note.D, 30));

        tiles.add(new Tile(Note.D, 34));
        tiles.add(new Tile(Note.D, 36));
        tiles.add(new Tile(Note.C1, 38));
        tiles.add(new Tile(Note.C1, 40));

        tiles.add(new Tile(Note.C1, 44));
        tiles.add(new Tile(Note.C1, 46));
        tiles.add(new Tile(Note.C1, 48));
        tiles.add(new Tile(Note.B, 50));
        tiles.add(new Tile(Note.B, 52));
        tiles.add(new Tile(Note.C1, 54));
        tiles.add(new Tile(Note.B, 56));

        tiles.add(new Tile(Note.E1, 60));
    }

    public List<Tile> tiles() {
        return tiles;
    }
}
