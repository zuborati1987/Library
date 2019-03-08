package com.codecool;

import com.codecool.members.Members;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MemberSerializer {
    public void serialize(Members members) {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/members.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(members);
            out.close();
            fileOut.close();
            System.out.println("Data serialized");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
