package utils;

import java.util.ArrayList;
import java.util.List;

import bean.Book;

public class Library {
    public static List<Book> getAllBook() {
	List<Book> books = new ArrayList<Book>();
	books.add(new Book(01, "Java", "third", "Herbert Schildt",
		"1987-05-12 00:00:00 -0800"));
	books.add(new Book(02, "C++", "second", "E.Balagurusamy",
		"1987-05-12 00:00:00 -0700"));
	return books;
    }

    public static void addBook(Book book) {
	List<Book> allBook = getAllBook();
	allBook.add(book);
    }

}
