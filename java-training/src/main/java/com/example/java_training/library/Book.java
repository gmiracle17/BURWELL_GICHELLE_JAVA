package com.example.java_training.library;

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
		if (!this.available) {
			System.out.println("Book is already borrowed. Sorry!\n");
		}
		System.out.println(this.title + " is successfully borrowed. Enjoy!\n");
		return this.available = false;
	}
	
	public Boolean returnBook() {
		System.out.println(this.title + " is successfully returned. Thank you!\n");
		return this.available = true;
	}
	
	public void getInfo() {
		System.out.println(this.title + " by " + this.author + " (Available : " + this.available + ")");
	}

	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() { 
		return this.author;
	}
}
