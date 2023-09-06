package dto;

public class book {
    private int ISBN;
    private String title;
    private String Author;
    private  status status;

    private static book instance;

    private book(){}

    // Public method to get the Singleton instance
    public static book getInstance(){
        if(instance==null)
        {
            instance = new book();
        }
        return instance;
    }
    private book(int isbn,String title, String author, status status){
        this.ISBN=isbn;
        this.title=title;
        this.Author=author;
        this.status=status;
    }




    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public dto.status getStatus() {
        return status;
    }

    public void setStatus(dto.status status) {
        this.status = status;
    }

    public static void setInstance(book instance) {
        book.instance = instance;
    }
}



