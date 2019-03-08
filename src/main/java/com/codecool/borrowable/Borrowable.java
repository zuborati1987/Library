package com.codecool.borrowable;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public abstract class Borrowable implements Serializable {
    private String name;
    private Date borrowedDate;
    private boolean borrowed;
    private Date returnDate;
    private String borrower;

    public Borrowable(String name) {
        this.name = name;
    }

    public void borrow(String person) {
        Calendar cal = Calendar.getInstance();
        borrowedDate = cal.getTime();
        cal.add(Calendar.MONTH, 3);
        returnDate = cal.getTime();
        borrowed = true;
        borrower = person;
    }

    public boolean isOverdue() {
        if(borrowed) {
            Calendar cal = Calendar.getInstance();
            Date currentDate = cal.getTime();
            if (currentDate.compareTo(borrowedDate) < 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public void returnItem() {
        borrowed = false;
        borrowedDate = null;
        returnDate = null;
        borrower = null;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        Borrowable compared = (Borrowable) object;

        if (this.name != compared.getName()) {
            return false;
        }

        return true;
    }


    public String getName() {
        return name;
    }

    public boolean isBorrowed() {
        return borrowed;
    }


    @Override
    public String toString() {
        return
            "Name: " + name + '\n' +
            "Date of borrowing:  " + borrowedDate + "\n" +
            "Borrowed status: " + borrowed + "\n" +
            "Return date: " + returnDate + "\n" +
            "Borrower name: " + borrower + '\n';
    }

    public String getBorrower() {
        return borrower;
    }
}
