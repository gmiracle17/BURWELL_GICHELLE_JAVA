package com.example.java_training.day2_library;

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
		
		// Expected to return "Janelle is not found."
		library.borrowBook("Janelle");
		
		// Expected to return "Harry Potter is successfully borrowed. Enjoy!"
		library.borrowBook("Harry Potter");
		
		// Expected to return "Harry Potter is already borrowed. Sorry!"
		library.borrowBook("Harry Potter");
		
		// Harry Potter should not be available
		System.out.println("\n---After borrowing Harry Potter---");
		library.showAllBooks();
		
		// Expected to return "Janelle is not found."
		library.returnBook("Janelle");
		
		// Expected to return "Harry Potter is successfully returned. Thank you!"
		library.returnBook("Harry Potter");
		
		// Harry Potter should be available again
		System.out.println("\n---After returning Harry Potter---");
		library.showAllBooks();
		
		
	}
}
