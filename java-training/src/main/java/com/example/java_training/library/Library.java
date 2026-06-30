package com.example.java_training.library;

import java.util.ArrayList;

public class Library {
	ArrayList<Book> library = new ArrayList<>();
	
	
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
			}
		}
	}
	
	void returnBook(String title) {
		for (Book book: library) {
			if (title == book.getTitle()) {
				book.returnBook();
			}
		}
	}
}
