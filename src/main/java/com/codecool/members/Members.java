package com.codecool.members;

import java.util.ArrayList;
import java.util.List;

public class Members implements java.io.Serializable {
    List<Person> members;

    public Members(){
        this.members = new ArrayList<>();
    }

    public List<Person> getMembers() {
        return members;
    }

    public void addMember(Person person) {
        if(!members.contains(person)) {
            this.members.add(person);
        } else {
            System.out.println("This person is already registered in the library!");
        }
    }

    public boolean isMember(String name) {
        for(Person member : members) {
            if(member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
