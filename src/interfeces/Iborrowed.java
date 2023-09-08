package interfeces;

import java.util.Date;

public interface Iborrowed {
    int borrow(int ISBN, int id , Date date_borrowed );
    int returned(int ISBN,Date date_returned );
}
