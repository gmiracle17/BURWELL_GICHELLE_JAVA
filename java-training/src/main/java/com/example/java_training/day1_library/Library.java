package com.example.java_training.day1_library;

import java.util.ArrayList;

public class Library {
	ArrayList<Book> library = new ArrayList<>();
	Boolean bookFound = false;
	
	void addBook (Book b) {
		library.add(b);
	}
	
	void showAllBooks() {
		for (Book book: library) {
			book.getInfo();
		}
		System.out.println();
	}
	
	void borrowBook(String title) {
		for (Book book: library) {
			if (title == book.getTitle()) {
				book.borrowBook();
				bookFound = true;
			}
		}
		if (!bookFound) {
			System.out.println(title + " is not found.");
		}
		bookFound = false; // return to default
	}
	
	void returnBook(String title) {
		for (Book book: library) {
			if (title == book.getTitle()) {
				book.returnBook();
				bookFound = true;
			}
		}
		if (!bookFound) {
			System.out.println(title + " is not found.");
		}
		
		bookFound = false; // return to default
	}
}
