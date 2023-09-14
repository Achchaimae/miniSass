package interfeces;

import dto.book;

public interface Ibook {

    void show();
    book add(book b);

    book update(book b);

    book delete(int isbn);

    book search(String title);
    book searchByIsbn(int ISBN);

    book borrow(int isbn);

    book returnBook(int isbn);
    void statistics();
    boolean isBorrowed(int isbn);
    book updateStatus(int isbn);
    book lostStatus(int isbn);
    int getBorrowedBooks();
    int getAvailableBooks();
    int getLostBooks();

}
