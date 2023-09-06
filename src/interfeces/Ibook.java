package interfeces;

import dto.book;

public interface Ibook {

    book show(book b);
    book add(book b);

    book update(book b);

    book delete(int isbn);

    book search(String title);
    book searchByIsbn(int ISBN);

}
