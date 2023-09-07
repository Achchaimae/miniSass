package interfeces;

import dto.borrower;

public interface Iborrower {

        borrower show(borrower b);
        borrower add(borrower b);

        borrower update(borrower b);

        borrower delete(int id);
        int search(String name);




}
