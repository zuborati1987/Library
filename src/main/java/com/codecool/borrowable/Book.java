package com.codecool.borrowable;

public class Book extends Borrowable {
    private int pages;

    public Book(String name, int pages) {
        super(name);
        this.pages = pages;
    }
}
