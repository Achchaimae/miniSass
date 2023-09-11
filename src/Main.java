import dto.book;
import dto.borrower;
import dto.status;
import implementation.bookImp;
import implementation.borrowed_booksImp;
import implementation.borrowerImp;
import interfeces.Iborrower;

import  javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello to miniSas Library!");
        String menu = JOptionPane.showInputDialog("Hello to miniSas Library \n <choose your need> \n\n" +
                "******* Menu ******* \n" +
                "1 : Show the list of books \n" +
                "2 : book's management \n" +
                "3 : Search about a book  \n" +
                "4 : borrow a book \n" +
                "5 : return a book\n" +
                "6 : Generate a static reports\n"+
                "7 : borrower's management\n");

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
                        String iSBN1 = JOptionPane.showInputDialog("Insert the ISBN of the book for delete");
                        int isbn = Integer.parseInt(iSBN1);
                        bookImp IMP = new bookImp();
                        book foundBook2 = IMP.searchByIsbn(isbn);

                        if (foundBook2 != null) {
                            String l1 = JOptionPane.showInputDialog("Do you want to delete this book?\n1: Yes\n2: No");
                            int yn = Integer.parseInt(l1);

                            switch (yn) {
                                case 1:
                                    IMP.delete(isbn);
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
                String iSBN1 = JOptionPane.showInputDialog("Insert the ISBN of the book for borrow");
                int isbn = Integer.parseInt(iSBN1);
                bookImp IMP = new bookImp();
                book foundBook2 = IMP.searchByIsbn(isbn);


                if(foundBook2!= null) {
                String l1 = JOptionPane.showInputDialog("Do you want to borrow this book?\n1: Yes\n2: No");
                    int yn = Integer.parseInt(l1);

                    switch (yn) {
                        case 1:
                            bookImp imp3 = new bookImp();
                            imp3.borrow(isbn);
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, "See you again!");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Invalid option");
                            break;
                    }

                }
            break;
            case 5 :
                String iSBN3 = JOptionPane.showInputDialog("Insert the ISBN of the book for return");
                int isbn2 = Integer.parseInt(iSBN3);
                bookImp IMP3 = new bookImp();
                book foundBook = IMP3.searchByIsbn(isbn2);
                if(foundBook!= null) {
                    String l1 = JOptionPane.showInputDialog("Do you want to return this book?\n1: Yes\n2: No");
                    int yn = Integer.parseInt(l1);

                    switch (yn) {
                        case 1:
                            bookImp imp3 = new bookImp();
                            imp3.returnBook(isbn2);
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, "See you again!");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Invalid option");
                            break;
                    }

                }
                break;
            case 6 :
                //call the function static report
                book b1 = book.getInstance();
                bookImp imp1 = new bookImp();
                imp1.statistics();


                break;
                case 7 :
                    String list4 = JOptionPane.showInputDialog("what action do you want \n " +
                            "1: add a borrower\n " +
                            "2 update a borrower\n" +
                            "3 delete a borrower\n\n");
                    int manag2= Integer.parseInt(list4);
                    switch (manag2){
                        case 1:
                            borrower b2 = borrower.getInstance();
                            b2.setName(JOptionPane.showInputDialog("Insert the name of the borrower"));
                            b2.setEmail(JOptionPane.showInputDialog("Insert the email of the borrower"));
                            b2.setPhone(JOptionPane.showInputDialog("Insert the phone of the borrower"));
                            b2.setAge(Integer.parseInt(JOptionPane.showInputDialog("Insert the age of the borrower")));
                            Iborrower imp3 = new borrowerImp();
                            imp3.add(b2);
                            break;


                        case 2 :
                            String name = JOptionPane.showInputDialog("Insert the name of the borrower for update");
                            Iborrower IMp = new borrowerImp();
                            int foundBorrower1= IMp.search(name);
                            //return the id from the search method
                            if(foundBorrower1!= 0) {
                                String l1 = JOptionPane.showInputDialog("Do you want to update this borrower?\n1: Yes\n2: No");
                                int yn = Integer.parseInt(l1);

                                switch (yn) {
                                    case 1:
                                        borrower b3 = borrower.getInstance();
                                        b3.setName(JOptionPane.showInputDialog("Insert the name of the borrower"));
                                        b3.setEmail(JOptionPane.showInputDialog("Insert the email of the borrower"));
                                        b3.setPhone(JOptionPane.showInputDialog("Insert the phone of the borrower"));
                                        b3.setAge(Integer.parseInt(JOptionPane.showInputDialog("Insert the age of the borrower")));
                                        IMp.update(b3);
                                        break;
                                    case 2:
                                        JOptionPane.showMessageDialog(null, "Have a good day!");
                                        break;
                                    default:
                                        JOptionPane.showMessageDialog(null, "Invalid choice");
                                        break;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Borrower not found!");
                            }

                            break;
                        case 3 :
                            borrower B = borrower.getInstance();
                            String name2 = JOptionPane.showInputDialog("Insert the name of the borrower for delete");
                            Iborrower IMP1 = new borrowerImp();
                            int foundBorrower = IMP1.search(name2);
                            //return the id from the search method
                            if(foundBorrower!= 0) {
                                String l1 = JOptionPane.showInputDialog("Do you want to delete this borrower?\n1: Yes\n2: No");
                                int yn = Integer.parseInt(l1);

                                switch (yn) {
                                    case 1:
                                        IMP1.delete(foundBorrower);
                                        break;
                                    case 2:
                                        JOptionPane.showMessageDialog(null, "See you again!");
                                        break;
                                    default:
                                        JOptionPane.showMessageDialog(null, "Invalid option");
                                        break;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Borrower not found!");
                            }

                            break;

                        default:
                            System.out.println("Invalid choice");
                    }
                    break;

            default:
                System.out.println("Invalid choice");

        }
                }

}