import dto.book;
import dto.status;
import implementation.bookImp;
import  javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello to miniSas Library!");
        String menu = JOptionPane.showInputDialog("Hello to miniSas Library \n <choose your need> \n\n" +
                "******* Menu ******* \n" +
                "1 : Show the list of books \n" +
                "2 : book management \n" +
                "3 : Search about a book  \n" +
                "4 : borrow a book \n" +
                "5 : return a book\n" +
                "6 : Generate a static reports\n");
        int list = Integer.parseInt(menu);

        switch (list){
            case 1 :
                book b = book.getInstance();
                bookImp imp = new bookImp();
                imp.show(b);
                break;
            case 2 :
                String list2 = JOptionPane.showInputDialog("what action do you want \n " +
                        "1: add a book\n " +
                        "2 update a book\n" +
                        "3 delete a book\n\n");
                int manag= Integer.parseInt(list2);
                switch (manag){
                    case 1:
                        book b2 = book.getInstance();
                        b2.setTitle(JOptionPane.showInputDialog("Insert the title of the book"));
                        b2.setAuthor(JOptionPane.showInputDialog("Insert the author of the book"));
                        b2.setISBN(Integer.parseInt(JOptionPane.showInputDialog("Insert the ISBN")));

                        b2.setStatus(dto.status.valueOf(JOptionPane.showInputDialog("Insert the status of the book")));
                        // Set the status for the book object
                        bookImp imp2 = new bookImp();
                        imp2.add(b2);
                       break;
                       case 2 :
                           String iSBN = JOptionPane.showInputDialog("Insert the ISBN of the book for update");
                           int ISBN3 = Integer.parseInt(iSBN);
                           bookImp imp3 = new bookImp();
                           book foundBook = imp3.searchByIsbn(ISBN3);

                           if (foundBook != null) {
                               String list3 = JOptionPane.showInputDialog("Do you want to update this book?\n1: Yes\n2: No");
                               int YN = Integer.parseInt(list3);

                               switch (YN) {
                                   case 1:
                                       String title2 = JOptionPane.showInputDialog("Insert the title of the book");
                                       String author2 = JOptionPane.showInputDialog("Insert the author of the book");
                                       String status2 = JOptionPane.showInputDialog("Insert the status of the book");
                                       foundBook.setTitle(title2);
                                       foundBook.setAuthor(author2);
                                       foundBook.setStatus(dto.status.valueOf(status2));
                                       // Assuming the update method is in the bookImp class
                                       imp3.update(foundBook);
                                       break;
                                   case 2:
                                       JOptionPane.showMessageDialog(null, "Have a good day!");
                                       break;
                                   default:
                                       JOptionPane.showMessageDialog(null, "Invalid choice");
                                       break;
                               }
                           } else {
                               JOptionPane.showMessageDialog(null, "Book not found!");
                           }

                        break;
                    case 3 :
                        String iSBN1 = JOptionPane.showInputDialog("Insert the ISBN of the book for update");
                        int isbn = Integer.parseInt(iSBN1);
                        bookImp IMP = new bookImp();
                        book foundBook2 = IMP.searchByIsbn(isbn); // Changed variable name from imp3 to IMP

                        if (foundBook2 != null) {
                            String l1 = JOptionPane.showInputDialog("Do you want to delete this book?\n1: Yes\n2: No");
                            int yn = Integer.parseInt(l1);

                            switch (yn) {
                                case 1:
                                    IMP.delete(isbn); // Changed book.delete(isbn1) to IMP.delete(isbn)
                                    break;
                                case 2:
                                    JOptionPane.showMessageDialog(null, "Thank you for keeping the book!");
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Invalid option");
                                    break;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Book not found!");
                        }


                        break;
                    default:
                        System.out.println("Invalid choice");
                }
                break;
            case 3 :
                String search = JOptionPane.showInputDialog("Insert the title of the book or the author");
                bookImp imp2 = new bookImp();
                imp2.search(search);
                break;
            case 4 :
                JOptionPane.showInputDialog("Insert the ISBN code for borrow ");
                break;
            case 5 :
                JOptionPane.showInputDialog("Insert the ISBN code for the return ");
                break;
            case 6 :
                JOptionPane.showMessageDialog(null, "6");
                break;
            default:
                System.out.println("Invalid choice");

        }
                }

}