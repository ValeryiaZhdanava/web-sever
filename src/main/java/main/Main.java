package main;

import handler.GetAllBooks;

public class Main {

    public static void main(String args[]) throws Throwable {
	Server.addHandler("GET", "/[a-z]", new GetAllBooks());
	Server.start();
    }

}
