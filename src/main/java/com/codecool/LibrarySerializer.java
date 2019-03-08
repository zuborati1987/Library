package com.codecool;

import com.codecool.borrowable.Library;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class LibrarySerializer {
    public void serialize(Library library) {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/library.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(library);
            out.close();
            fileOut.close();
            System.out.println("Data serialized");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
