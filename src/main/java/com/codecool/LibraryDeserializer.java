package com.codecool;

import com.codecool.borrowable.Library;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LibraryDeserializer {
    public Library deserialize() throws IOException, ClassNotFoundException{
        Library library = null;
            FileInputStream fileIn = new FileInputStream("src/main/resources/library.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            library = (Library) in.readObject();
            in.close();
            fileIn.close();
        return library;
    }
}
