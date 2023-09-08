package dto;

public class borrowed_books {
    private  int book_id;
    private int borrower_id;
    private String date_borrowed;
    private String date_returned;
    private static borrowed_books instance;
    private borrowed_books(){}
    // Public method to get the Singleton instance
    public static borrowed_books getInstance(){
        if(instance==null)
        {
            instance = new borrowed_books();
        }
        return instance;
    }
    private borrowed_books(int book_id,int borrower_id, String date_borrowed, String date_returned){
        this.book_id=book_id;
        this.borrower_id=borrower_id;
        this.date_borrowed=date_borrowed;
        this.date_returned=date_returned;
    }
    private borrowed_books(int book_id,int borrower_id, String date_borrowed){
        this.book_id=book_id;
        this.borrower_id=borrower_id;
        this.date_borrowed=date_borrowed;
    }
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getBorrower_id() {
        return borrower_id;
    }

    public void setBorrower_id(int borrower_id) {
        this.borrower_id = borrower_id;
    }

    public String getDate_borrowed() {
        return date_borrowed;
    }

    public void setDate_borrowed(String date_borrowed) {
        this.date_borrowed = date_borrowed;
    }

    public String getDate_returned() {
        return date_returned;
    }

    public void setDate_returned(String date_returned) {
        this.date_returned = date_returned;
    }
}
