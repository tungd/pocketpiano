package com.pocketpiano.pocketpiano.exercises;

import com.pocketpiano.pocketpiano.instruments.PianoNodes.Note;

import java.util.ArrayList;
import java.util.List;

public class HappyBirthDay {

    List<Tile> tiles;

    public HappyBirthDay() {
        tiles = new ArrayList<>();

        tiles.add(new Tile(Note.C1, 0));
        tiles.add(new Tile(Note.C1, 2));
        tiles.add(new Tile(Note.D, 4));
        tiles.add(new Tile(Note.C1, 6));
        tiles.add(new Tile(Note.F, 8));
        tiles.add(new Tile(Note.E, 10));

        tiles.add(new Tile(Note.C1, 14));
        tiles.add(new Tile(Note.C1, 16));
        tiles.add(new Tile(Note.D, 18));
        tiles.add(new Tile(Note.C1, 20));
        tiles.add(new Tile(Note.G, 22));
        tiles.add(new Tile(Note.F, 24));

        tiles.add(new Tile(Note.C1, 28));
        tiles.add(new Tile(Note.C1, 30));
        tiles.add(new Tile(Note.C, 32));
        tiles.add(new Tile(Note.A, 34));
        tiles.add(new Tile(Note.F, 36));
        tiles.add(new Tile(Note.E, 38));
        tiles.add(new Tile(Note.D, 40));
    }

    public List<Tile> tiles() {
        return tiles;
    }
}
