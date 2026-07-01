package com.example.java_training.day1_library;

public class Book {
	String title;
	String author;
	Boolean available;
	
	public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }
	
	public String setTitle (String title){
		return this.title = title;
	}
	
	public String setAuthor (String author) {
		return this.author = author;
	}
	
	public Boolean borrowBook() {
		if (!available) {
			System.out.println(title + " is already borrowed. Sorry!\n");
		}
		else {
			System.out.println(title + " is successfully borrowed. Enjoy!\n");
		}
		return this.available = false;
	}
	
	public Boolean returnBook() {
		System.out.println(title + " is successfully returned. Thank you!\n");
		return this.available = true;
	}
	
	public void getInfo() {
		System.out.println(title + " by " + author + " (Available : " + available + ")");
	}

	public String getTitle() {
		return this.title;
	}
}
