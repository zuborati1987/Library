package com.codecool.members;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Person implements Serializable {
    private String name;
    private Status status;
    private int items;
    private Date registration;
    private Date regEnd;

    public String getName() {
        return name;
    }


    public boolean addItems() {
        if (status.equals(status.STUDENT)) {
            if (items < 5) {
                items++;
                return true;
            } else {
                return false;
            }
        } else if (status.equals(status.LOCAL)) {
            if (items < 3) {
                items++;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void takeItem() {
        if (items > 0) {
            items--;
        }
    }


    public Person(String name, Status status) {
        this.name = name;
        this.status = status;
        this.items = 0;
        Calendar cal = Calendar.getInstance();
        this.registration = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        this.regEnd = cal.getTime();
    }

    @Override
    public String toString() {
        return
            "Name:" + name + '\n' +
                "Status: " + status + "\n" +
                "Number of borrowed items: " + items + "\n" +
                "Registration: " + registration + "\n" +
                "End of registration: " + regEnd;
    }
}
