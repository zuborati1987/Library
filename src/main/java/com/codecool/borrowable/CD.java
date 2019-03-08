package com.codecool.borrowable;

public class CD extends Borrowable {
    private int tracks;

    public CD(String name, int tracks) {
        super(name);
        this.tracks = tracks;
    }
}
