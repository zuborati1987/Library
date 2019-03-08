package com.codecool;

import com.codecool.members.Members;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MemberDeserializer {
    public Members deserialize() throws IOException, ClassNotFoundException {
        Members members = null;
            FileInputStream fileIn = new FileInputStream("src/main/resources/members.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            members = (Members) in.readObject();
            in.close();
            fileIn.close();
        return members;
    }
}
