package com.codecool;

// java -cp target/JAR-1.0-SNAPSHOT.jar com.codecool.Main

import com.codecool.borrowable.Book;
import com.codecool.borrowable.Borrowable;
import com.codecool.borrowable.CD;
import com.codecool.borrowable.Library;
import com.codecool.members.Members;
import com.codecool.members.Person;
import com.codecool.members.Status;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Members members = new Members();
        Library library = new Library();
        LibrarySerializer libser = new LibrarySerializer();
        MemberSerializer memser = new MemberSerializer();
        try {
            members = new MemberDeserializer().deserialize();
            library = new LibraryDeserializer().deserialize();
        } catch (Exception e) {
            System.out.println("Empty library database initialized \n");
        }
        int choice;

        do {
            System.out.println("Welcome to Bivajbasznád Local Library! \n");
            System.out.println("Your option are the following: \n\n" +
                "1. Register new member \n" +
                "2. Register new library item \n" +
                "3. Borrow item \n" +
                "4. Return item \n" +
                "5. Find item \n" +
                "6. Find member \n" +
                "7. List overdue books \n" +
                "8. Exit library system");
            choice = Integer.parseInt(reader.nextLine());
            switch (choice) {
                case (1):
                    System.out.println("New members name: ");
                    String name = reader.nextLine();
                    int chosen;
                    Status status;
                    System.out.println("(1) Student or (2) Local?");
                    chosen = Integer.parseInt(reader.nextLine());
                    if (chosen == 1) {
                        status = Status.STUDENT;
                    } else if (chosen == 2) {
                        status = Status.LOCAL;
                    } else {
                        System.out.println("Invalid number");
                        break;
                    }
                    members.addMember(new Person(name, status));
                    break;
                case (2):
                    System.out.println("New item's title: ");
                    String title = reader.nextLine();
                    System.out.println("(1) Book or (2) CD? ");
                    chosen = Integer.parseInt(reader.nextLine());
                    if (chosen == 1) {
                        System.out.println("How many pages long?");
                        int pages = Integer.parseInt(reader.nextLine());
                        library.addItem(new Book(title, pages));
                        break;
                    } else if (chosen == 2) {
                        System.out.println("How many tracks?");
                        int tracks = Integer.parseInt(reader.nextLine());
                        library.addItem(new CD(title, tracks));
                        break;
                    } else {
                        System.out.println("Invalid number");
                        break;
                    }
                case (3):
                    System.out.println("Name of the member: ");
                    name = reader.nextLine();
                    System.out.println("Name of the item: ");
                    String item = reader.nextLine();
                    if (members.isMember(name)) {
                        if (library.isInStock(item)) {
                            for (Borrowable libItem : library.getStock()) {
                                if (libItem.getName().equals(item)) {
                                    if (libItem.isBorrowed() == false) {
                                        libItem.borrow(name);
                                        for (Person member : members.getMembers()) {
                                            if (member.getName().equals(name)) {
                                                member.addItems();
                                            }
                                        }
                                    } else {
                                        System.out.println("The item is already borrowed.");
                                    }
                                }
                            }
                        } else {
                            System.out.println("No such item in stock!");
                        }
                    } else {
                        System.out.println("No such member, please register!");
                    }
                    break;
                case (4):
                    System.out.println("Name of the item: ");
                    String toReturn = reader.nextLine();
                    if (library.isInStock(toReturn)) {
                        for (Borrowable libItem : library.getStock()) {
                            if (libItem.getName().equals(toReturn)) {
                                if (libItem.isBorrowed() == true) {
                                    String borrower = libItem.getBorrower();
                                    for (Person member : members.getMembers()) {
                                        if (member.getName().equals(borrower)) {
                                            member.takeItem();
                                        }
                                    }
                                    libItem.returnItem();
                                }
                            }
                        }
                    } else {
                        System.out.println("No such item in stock!");
                    }
                    break;
                case (5):
                    System.out.println("Name of the item: ");
                    String toFind = reader.nextLine();
                    if (library.isInStock(toFind)) {
                        for (Borrowable libItem : library.getStock()) {
                            if (libItem.getName().equals(toFind)) {
                                System.out.println(libItem.toString());
                            }
                        }
                    } else {
                        System.out.println("No such item in stock!");
                    }
                    break;
                case (6):
                    System.out.println("Name of the member: ");
                    name = reader.nextLine();
                    if (members.isMember(name)) {
                        for (Person member : members.getMembers()) {
                            if (member.getName().equals(name)) {
                                System.out.println(member.toString());
                            }
                        }
                    } else {
                        System.out.println("No such member in the library!");
                    }
                    break;
                case (7):
                    int x = 0;
                    for (Borrowable libItem : library.getStock()) {
                        if (libItem.isOverdue()) {
                            System.out.println(libItem.toString());
                            x++;
                        }
                    }
                    if (x == 0) {
                        System.out.println("No books are overdue!");
                    }
                    break;
                default:
                    break;

            }
        } while (choice != 8);
        libser.serialize(library);
        memser.serialize(members);
    }
}
