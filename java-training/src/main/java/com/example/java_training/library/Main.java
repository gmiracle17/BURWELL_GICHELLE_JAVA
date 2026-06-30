package com.example.java_training.library;

public class Main {
	public static void main(String[] args) {
		Library library = new Library();
		
		Book harryPotter = new Book("Harry Potter", "Janelle");
		Book bestIBMer = new Book("Best IBMer", "Philip");
		Book matcha = new Book("Eren", "Eren");
		
		library.addBook(harryPotter);
		library.addBook(bestIBMer);
		library.addBook(matcha);
		
		System.out.println("---Before borrowing Harry Potter---");
		library.showAllBooks();
		
		library.borrowBook("Janelle");
		
		library.borrowBook("Harry Potter");
		System.out.println("\n---After borrowing Harry Potter---");
		library.showAllBooks();
		
		library.returnBook("Janelle");
		library.returnBook("Harry Potter");
		System.out.println("\n---After returning Harry Potter---");
		library.showAllBooks();
		
		
	}
}
